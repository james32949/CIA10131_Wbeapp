//JS 動態路徑
var MyPoint = "/QueryMember"; //對應XML的 <url-pattern>/Ajax</url-pattern>  記得檢查路徑是否正確
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "http://" + window.location.host + webCtx + MyPoint;
var imgPointURL ="http://"+ window.location.host + webCtx + "/DBReader?memberId=";

$(document).ready(function(){
  $.post({
    url:endPointURL,
    data:{
      "action":"getUserInfomation"
    },
    datatype:"json",
    success:function(data){

      $("#userId").text(data.memberAccount)
      $("#userName").attr("placeholder", data.memberName)
      $("#userEmail").attr("placeholder", data.memberEmail)
      $("#userPhone").attr("placeholder", data.memberPhone)
      $("#userAddress").attr("placeholder", data.memberAddress)
      $("#userBirthday").attr("placeholder", data.memberBirthday)
      $("#memberImg").attr("src", imgPointURL+data.memberId)

    }
  })
  
})

$("#logout").click(function(e){
  e.preventDefault()
  // console.log("OK")
  $.post({
    url:endPointURL,
    data:{
      "action":"userLogout"
    },
    datatype:"json",
    success:function(){
      window.localStorage.clear();
      location.reload(true);
      window.location.href = "/CIA10131_Webapp/backend/member/Login.html";
    }
  })
}) 