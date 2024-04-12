<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="member.*"%>


<%
  	MemberService memSvc = new MemberService();
    List<MemberVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);

%>


<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>

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
</style>
 
<style>
.main {
  width: 100%;
  margin: 20px auto;
}
table {
  border-spacing: 0;
  width: 100%;
}
tr {
  text-align: center;
}
th {
  padding: 20px;
}
table tbody tr:nth-child(odd){
  background-color: #eee
}

table tbody tr:last-child td:first-child {
  border-radius: 0 0 0 5px;
}

table tbody tr:last-child td:last-child {
  border-radius: 0 0 5px 0;
}
</style>

</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>-- 所有會員資料 --</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<div class="main">
<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>Email</th>
		<th>電話</th>
		<th>地址</th>
		<th>狀態</th>
		<th>性別</th>
		<th>生日</th>
		<th>圖像</th>
	</tr>

	<c:forEach var="memberVO" items="${list}" >
		
		<tr>
			<td>${memberVO.member_id}</td>
			<td>${memberVO.member_name}</td>
			<td>${memberVO.member_account}</td>
			<td>${memberVO.member_password}</td>
			<td>${memberVO.member_email}</td>
			<td>${memberVO.member_phone}</td> 
			<td>${memberVO.member_address}</td>
			<td>${memberVO.member_state}</td>
			<td>${memberVO.member_gender}</td>
			<td>${memberVO.member_birthday}</td>
			<td>${memberVO.member_img}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="member_id"  value="${memberVO.member_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="member_id"  value="${memberVO.member_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>

</body>
</html>