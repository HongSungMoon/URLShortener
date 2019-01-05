<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form action="action_page.php">
		<div class="container">
			<label for="uname"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="uname" required> <label
				for="psw"><br>
			<b>Password</b></label> <input type="password" placeholder="Enter Password"
				name="psw" required>
			<button type="submit">Login</button>
		</div>


	</form>
	<button type="button" id="back">back</button>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script>
		$('#back').click(function() {
			window.location.replace("http://localhost:8080/");
		});
	</script>
</body>
</html>