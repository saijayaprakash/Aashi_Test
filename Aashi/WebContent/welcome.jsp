<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<script type="text/javascript" src="js/app.module.js"></script>
		<script type="text/javascript" src="js/app.controller.js"></script>
</head>
<body ng-app="welcome" ng-controller="welcomeController"> 
	<%
		try{
			HttpSession sessionWelcome=request.getSession();
			String user=(String)sessionWelcome.getAttribute("user");
			if(user==null)
			{
				response.sendRedirect("index.jsp");
			}
			
		}catch(Exception e){}
	%>
	<form action="reset" method="GET">
	<h1>Hello <font color="red">{{user.fullname}}</font></h1>
	<h3 ng-hide="editing" ng-click="editing=true">Your password is {{user.password}}</h1> 
		<span ng-show="editing"> 
			New Password : <input type="text" name="rpass">
			<input type="submit" value="reset" name="reset">
			<input type="button" ng-click="editing=false" value="cancel">
		</span>
 	<h3>Your username is {{user.username}}</h3>
	</form> 
	<form action="logout" method="post">
		<input type="submit" value="logout">
	</form>
</body>
</html>