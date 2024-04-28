//JS 動態路徑
var MyPoint = "/QueryMember"; //對應XML的 <url-pattern>/Ajax</url-pattern>  記得檢查路徑是否正確
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "http://" + window.location.host + webCtx + MyPoint;

$(document).ready(function(){ //ready事件 DOM載入完成後觸發
  console.log("Ajax GO!!");

  $.post({
    url:endPointURL,
    data:{"action":"getAllMember"},
    datatype:"json",
    success:function(data){
      

      for(let i = (Object.keys(data).length)-1; i >= 0; i--){

        //console.log(data[i].memberName)
        $(function(){ //加入表格
        let html =`
          <tr>
          <th>${data[i].memberId}</th>
          <td>${data[i].memberName}</td>
          <td>${data[i].memberAccount}</td>
          <td>${data[i].memberEmail}</td>
          <td>${data[i].memberPhone}</td>
          <td>${data[i].memberAddress}</td>
          <td>${(data[i].memberGender == 0)? "男":"女"}</td>
          <td>${data[i].memberBirthday}</td>
          <td style="color: ${(data[i].memberState == 2)? "red":(data[i].memberState == 0)? "gray":"green"}">${(data[i].memberState == 2)? "停權":(data[i].memberState == 0)? "未驗證":"已驗證"}</td>
          <td><button id="button_State" style="line-height: 1;" class="${(data[i].memberState == 2)? "btn btn-primary rounded-pill m-2":"btn btn-danger rounded-pill m-2"}">${(data[i].memberState == 2)? "復原":"停權"}</button></td> 
          </tr>       
          `;
          $("#table_member").prepend(html) //寫入
        })
      }
    }
  })
});

$(document).on("click", "#button_State", function(){ //點擊修改按鈕
  // console.log("OK");
  let currentRow = this.closest("tr");
  // console.log(currentRow);
  let th = currentRow.children
  // console.log(th)
  let memberId = th[0].innerText; // 獲取MemberID
  let memberState = th[8].innerText;
  // console.log("memberID="+memberId) 
  // console.log("memberState="+memberState)

  $.post({ //發送POST請求
    url:endPointURL,   //送出路徑
    data:{             //送出資料     
      "action":"checkState",
      "memberId":memberId,
      "memberState":memberState
    },
    datatype:"json",
    success:function(data){ //接收回傳參數 使用js 修改頁面

      let html =`
      <tr>
      <th>${data.memberId}</th>
      <td>${data.memberName}</td>
      <td>${data.memberAccount}</td>
      <td>${data.memberEmail}</td>
      <td>${data.memberPhone}</td>
      <td>${data.memberAddress}</td>
      <td>${(data.memberGender == 0)? "男":"女"}</td>
      <td>${data.memberBirthday}</td>
      <td style="color: ${(data.memberState == 2)? "red":(data.memberState == 0)? "gray":"green"}">${(data.memberState == 2)? "停權":(data.memberState == 0)? "未驗證":"已驗證"}</td>
      <td><button id="button_State" style="line-height: 1;" class="${(data.memberState == 2)? "btn btn-primary rounded-pill m-2":"btn btn-danger rounded-pill m-2"}">${(data.memberState == 2)? "復原":"停權"}</button></td> 
      </tr>      
      `;

      $(currentRow.previousSibling).after(html)
      currentRow.remove()
    }
  })
})

$(document).on("click", "#buttonMemberQuery", function(){ //點擊查詢按鈕
  // console.log("OK!!");
  //取值
  let inputName = $("#queryMemberId").val();
  let inputPheon = $("#queryMemberPhone").val();
  let inputEmail = $("#queryMemberEmail").val();

  // console.log(inputName);
  // console.log(inputPheon);
  // console.log(inputEmail);

  $.post({
    url:endPointURL,
    data:{
      "action":"queryMember",
      "memberName":inputName,
      "memberPhone":inputPheon,
      "memberEmail":inputEmail
    },
    datatype:"json",
    success:function(data){
      $("#table_member").remove() 

      $("#thead_member").after(`</tbody">`)
      $("#thead_member").after(`<tbody id="table_member">`)

      for(let i = (Object.keys(data).length)-1; i >= 0; i--){

        //console.log(data[i].memberName)
        $(function(){ //加入表格
        let html =`

          <tr>
          <th>${data[i].memberId}</th>
          <td>${data[i].memberName}</td>
          <td>${data[i].memberAccount}</td>
          <td>${data[i].memberEmail}</td>
          <td>${data[i].memberPhone}</td>
          <td>${data[i].memberAddress}</td>
          <td>${(data[i].memberGender == 0)? "男":"女"}</td>
          <td>${data[i].memberBirthday}</td>
          <td style="color: ${(data[i].memberState == 2)? "red":(data[i].memberState == 0)? "gray":"green"}">${(data[i].memberState == 2)? "停權":(data[i].memberState == 0)? "未驗證":"已驗證"}</td>
          <td><button id="button_State" style="line-height: 1;" class="${(data[i].memberState == 2)? "btn btn-primary rounded-pill m-2":"btn btn-danger rounded-pill m-2"}">${(data[i].memberState == 2)? "復原":"停權"}</button></td> 
          </tr>
      
          `;

          $("#table_member").append(html) //寫入

        })
      }

    }
  })


})



$("#queryMemberId, #queryMemberPhone, #queryMemberEmail").keydown(function(e){
  // console.log(e.keyCode);
  if(e.keyCode === 13){
    // console.log("Enter");
    $("#buttonMemberQuery").click();
  }
})

$("#queryMemberId").focus(function(){

  $("#queryMemberPhone").val("");
  $("#queryMemberEmail").val("");
})

$("#queryMemberPhone").focus(function(){

  $("#queryMemberId").val("");
  $("#queryMemberEmail").val("");
})

$("#queryMemberEmail").focus(function(){

  $("#queryMemberPhone").val("");
  $("#queryMemberId").val("");
})