<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>Welcome</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
</head>

<body>
	<h2>Welcome admin to the site!</h2>

	<table>
		<c:forEach items="${usersList}" var="user">
			<tr>
				<td><c:out value="${user.userId}" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.2/js/toastr.min.js">
		
	</script>

	<script>
		window.onload = function() {
				Command: toastr["success"]("Welcome ${userId} to the site!")

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
	</script>
</body>

</html>