<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="member.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<% 
MemberVO memVO = (MemberVO) request.getAttribute("memVO");//EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>-- �|����� --</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>--�|�����--</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���m�W</th>
		<th>�|���b��</th>
		<th>�|���K�X</th>
		<th>Email</th>
		<th>�q��</th>
		<th>�a�}</th>
		<th>���A</th>
		<th>�ʧO</th>
		<th>�ͤ�</th>
		<th>�Ϲ�</th>
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