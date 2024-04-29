//JS 動態路徑
var MyPoint = "/QueryMember"; //對應XML的 <url-pattern>/Ajax</url-pattern>  記得檢查路徑是否正確
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "http://" + window.location.host + webCtx + MyPoint;


$(document).on("click", "#loginButton", function(){
  console.log("loginButton")

  let inputAccount = $("#floatingInput").val()
  let inputPassword = $("#floatingPassword").val()

  console.log(inputAccount !== "")
  console.log(inputPassword)

  if(inputAccount === ""){
    $("#errorAccount").css("display", "block")
  }
  if(inputPassword === ""){
    $("#errorPassword").css("display", "block")
  }
  if(inputAccount !== "" && inputPassword !== ""){
      $.post({
        url:endPointURL,
        data:{
          "action":"userLogin",
          "inputAccount": inputAccount,
          "inputPassword":inputPassword
        },
        datatype:"json",
        success:function(data){
          
        }
      })
  }
})