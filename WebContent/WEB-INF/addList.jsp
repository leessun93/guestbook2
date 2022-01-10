<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
 <%@ page import="com.javaex.vo.Guestbookvo" %>   
 <%@ page import="com.javaex.dao.GuestbookDao" %>
 <%
 
 	GuestbookDao guestbookDao = new GuestbookDao();
 
 	List<Guestbookvo> guestbookList = guestbookDao.getList();
 %>   
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--등록영역-->
	<form action="/guestbook2/gbc" method="get">
		<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="uname" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="upswd" value=""></td>
			</tr>
			<tr>
				<td colspan="4">
				<textarea cols="66" rows="5" name="textarea"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<button type= "submit">글작성</button>
				</td>
				<td>
				<input type="hidden" name="action" value="add">
				</td>
			</tr>
		</table>
	</form>
	
	<!-- /등록영역 -->
	<!-- 리스트 영역 -->
<%
for(int i=0; i<guestbookList.size(); i++){
%>
		<table border="1" width="500px">
			<tr>
				<td><%=guestbookList.get(i).getNo() %></td>
				<td><%=guestbookList.get(i).getName()%></td>
				<td><%=guestbookList.get(i).getReg_date() %></td>
				<td><a href="/guestbook2/gbc?action=deleteForm&id=<%=guestbookList.get(i).getNo()%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan = "4"><br>
				<%=guestbookList.get(i).getContent() %>
				</td>
				
			</tr>
		</table>	
		<br>			
			
			
			
		
		
<%	
}
%>

	<!-- /리스트 영역 -->
	
</body>
</html>