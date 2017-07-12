<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kingmaker Space</title>
</head>
<body>
	<%
		try{
			HttpSession sessionAdmin=request.getSession();
			String user=(String)sessionAdmin.getAttribute("user");
			if(user==null)
				response.sendRedirect("index.jsp");
		}
		catch(Exception e){}
	%>
	<h1>Hello Kingmaker !!</h1>
	<form action="logout" method="POST">
		<input type="submit" value="> Close It for Now <">
	</form>
	<form action="sessionDetails" method="GET">
		<input type="submit" value="> Get Session Details <">
	</form>
	<form action="getUsers" method="GET">
		<input type="submit" value="> All Users Details <">
	</form>
</body>
</html>