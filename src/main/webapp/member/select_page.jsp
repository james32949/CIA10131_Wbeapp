<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Member Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: skyblue;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>�|������</h3></td></tr>
</table>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMember.jsp'>�����|��</a><br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="member.do" >
        <b>��J�|���s��:</b>
        <input type="text" name="member_id" >
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="memSvc" scope="page" class="member.MemberService" />
   
  <li>
     <FORM METHOD="post" ACTION="member.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="member_id">
         <c:forEach var="MemberVO" items="${memSvc.all}" > 
          <option value="${MemberVO.member_id}">${MemberVO.member_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="member.do" >
       <b>��ܷ|���m�W:</b>
       <select size="1" name="member_id">
         <c:forEach var="MemberVO" items="${memSvc.all}" > 
          <option value="${MemberVO.member_id}">${MemberVO.member_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addMember.jsp'>�s�W�|��</a></li>
</ul>

</body>
</html>