<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
<title>Login Page</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
	<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
<style>
.login-form {
	width: 400px;
	height: 230px;
	background-color: #34568B;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%);
}

</style>
</head>

<body>
	
	<div class="login-form">
	
		<div class="container-fluid">
			<form:form method="post" action="register" modelAttribute="user">	
				<div class="mt-3">
					<form:input type="text" class="form-control" path="userId"
						placeholder="User ID" />
				</div>
				<div class="mt-3">
					<form:input type="password" class="form-control" path="password"
						placeholder="Password" />
				</div>
				<form:button type="submit" class="btn btn-dark btn-block mt-3">Register</form:button>
			</form:form>

		</div>
		
	</div>

	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.2/js/toastr.min.js">
		
	</script>

	<script>
		window.onload = function() {
			if ("${errorMsg}" != "") {
				Command: toastr["error"]("${errorMsg}")

				toastr.options = {
					"closeButton" : false,
					"debug" : false,
					"newestOnTop" : false,
					"progressBar" : false,
					"positionClass" : "toast-top-right",
					"preventDuplicates" : false,
					"onclick" : null,
					"showDuration" : "5000",
					"hideDuration" : "1000",
					"timeOut" : "5000",
					"extendedTimeOut" : "1000",
					"showEasing" : "swing",
					"hideEasing" : "linear",
					"showMethod" : "fadeIn",
					"hideMethod" : "fadeOut"
				}
			}
		}
	</script>
</body>

</html>