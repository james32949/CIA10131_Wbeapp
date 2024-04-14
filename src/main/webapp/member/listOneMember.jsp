<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="member.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<% 
MemberVO memVO = (MemberVO) request.getAttribute("memVO");//EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>-- 會員資料 --</title>

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
  table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>--會員資料--</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

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
	<tr>
		<td><%=memVO.getMember_id()%></td>
		<td><%=memVO.getMember_name()%></td>
		<td><%=memVO.getMember_account()%></td>
		<td><%=memVO.getMember_password()%></td>
		<td><%=memVO.getMember_email()%></td>
		<td><%=memVO.getMember_phone()%></td>
		<td><%=memVO.getMember_address()%></td>
		<td><%=memVO.getMember_state()%></td>
		<td><%=memVO.getMember_gender()%></td>
		<td><%=memVO.getMember_birthday()%></td>
		<td><%=memVO.getMember_img()%></td>
		

	</tr>
</table>

</body>
</html>