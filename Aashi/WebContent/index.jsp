<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login</title>

		<!-- Scripts -->
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

		<!-- StyleSheets -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" href="css/style.css">

		<!-- User Scripts -->
		<script type="text/javascript" src="js/app-module.js"></script>
		<script type="text/javascript" src="js/app-controller.js"></script>
</head>
<body ng-app="login" ng-controller="loginController">
		<h1>Hello World</h1>
		<script type="text/javascript">
		function checkLoginState() {
	    FB.getLoginStatus(function(response) {
	      	statusChangeCallback(response);
		   });
		 }

		function statusChangeCallback(response) {
		    if (response.status === 'connected') {
		    	console.log("Access Key : ",response.authResponse.accessToken);
		    	document.getElementById('fbAccessKey').value = response.authResponse.accessToken;
		       	testAPI();
		    } else {
		      	console.log("Not Connected !");
		    }
		 }

		 function testAPI() {
		    FB.api('/me', function(response) {
			    console.log("My data",response);
			    FB.AppEvents.logEvent("Logged in to Aashi");
			    document.getElementById('fbuser').value = response.name;
			    document.getElementById('fbId').value = response.id;
			    document.getElementById('fbForm').submit();
		    });
		  }
		</script>

		<img src="static/img/149H.jpg" class="background">
		<center><h1 class="martop50">Welcome to Your '$' Money Portal..!</h1></center>
		<div class="input">
			<button type="button" class="btn btn-success" ng-click="signIn()"><i class="material-icons" style="vertical-align: inherit;">exit_to_app</i> Sign in </button>
			<button type="button" class="btn btn-success" ng-click="signUp()">Sign Up <i class="material-icons" style="vertical-align: inherit;">get_app</i></button>
			<div ng-if="signin" class="martop10">
				<form action="login" method="post">
					<table>
						<tr>
							<td class="red">Username</td>
							<td>:<input type="text" name="username"></td>
						</tr>
						<tr>
							<td class="red">Password</td>
							<td>:<input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Login"></td>
						</tr>
					</table>
				</form>
			</div>
			<div ng-if="signup" class="martop10">
				<form action="signup" method="post">
					<table>
						<tr>
							<td>Username</td>
							<td>:<input type="text" name="username"></td>
						</tr>
						<tr>
							<td>Password</td>
							<td>:<input type="password" name="password"></td>
						</tr>
						<tr>
							<td>FullName</td>
							<td>:<input type="text" name="fullname"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="SignUp"></td>
						</tr>
					</table>
				</form>
			</div>
			<div ng-if="signin" class="fb-login-button martop10" scope="public_profile,email,publish_actions" onlogin="checkLoginState();" data-width="200" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="false" data-use-continue-as="true"></div>

			<!-- FB Form -->
			<form action="login" method="POST" id="fbForm">
				<input type="hidden" id="fbuser" name="fbuser">
				<input type="hidden" id="fbId" name="fbId">
				<input type="hidden" id="fbAccessKey" name="fbAccessKey">
			</form>
		<div>

	</body>
</html>