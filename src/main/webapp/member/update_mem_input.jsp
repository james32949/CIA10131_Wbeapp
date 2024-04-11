<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.*"%>

<% 
   MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
#preview{
    border: 1px solid black;
    display: inline-block;
    width: 200px;
    height: 200px;
    min-height: 150px;
    position: relative;
 }
#preview span.text{
    position: absolute;
    display: inline-block;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    z-index: -1;
    color: lightgray;
}
#preview img.preview_img{
    width: 100%;
}
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料修改</h3>
		 <h4><a href="select_page.jsp">首頁</a></h4>
	</td></tr>
</table>

<h3>會員資料:</h3>


<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1">
<table>
	<tr>
		<td>會員編號:</td>
		<td>${param.member_id}</td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="member_name" value="<%=memVO.getMember_name() %>" size="45"/></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="member_account" value="<%=memVO.getMember_account() %>" size="45" /></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="member_password"   value="<%=memVO.getMember_password() %>" size="45"/></td>
	</tr>
	
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="member_email" value="<%=memVO.getMember_email() %>" size="45"/></td> 
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="member_phone"   value="<%=memVO.getMember_phone() %>" size="45"/></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="member_address"  value="<%=memVO.getMember_address() %>" size="45"/></td>
	</tr>
	
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="member_state"  value="<%=memVO.getMember_state() %>" size="45"/></td>
	</tr>
	
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="member_gender"  value="<%=memVO.getMember_gender() %>" size="45"/></td>
	</tr>
	
	<tr>
		<td>生日:</td>
		<td><input name="member_birthday" id="f_date1" size="45"/></td>
	</tr>
	
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="member_img" id="p_file" @change="previewObjectURL"/></td>
		
	</tr>




</table>
<div id="preview"><img width=100% src="${pageContext.request.contextPath}/DBReader"></div>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="member_id" value="<%=memVO.getMember_id() %>">
<input type="submit" value="送出資料"></FORM>

<script>
	var div_preview = document.getElementById("preview"); 
	var but_p_file = document.getElementById("p_file");
	var preview_span=`<span class="text">預覽圖</span>`;
	but_p_file.addEventListener("change",function(){ 

  		var reader = new FileReader();    
  		reader.readAsDataURL(this.files[0]);
 
  		reader.addEventListener("load",function(){
    		let img_html = `<img class = "preview_img" src="${reader.resul}" >`;
			div_preview.innerHTML = img_html;
		})
	})
</script>

</body>



<!-- ========================================= datetimepicker ========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memVO.getMember_birthday()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], 
           //startDate:	            '2017/07/10',  
           //minDate:               '-1970-01-01', 
           //maxDate:               '+1970-01-01'  
        });
        
        //----------------------------------------------------------------------------------------------------------------------------------------
   		
      
</script>
</html>