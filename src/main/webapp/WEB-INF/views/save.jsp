<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add User</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="https://cloud.github.com/downloads/digitalBush/jquery.maskedinput/jquery.maskedinput-1.3.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>


<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cloud.github.com/downloads/digitalBush/jquery.maskedinput/jquery.maskedinput-1.3.min.js"></script>



<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js"></script>

<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>



<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
<script type="text/javascript"
	src="https://cloud.github.com/downloads/digitalBush/jquery.maskedinput/jquery.maskedinput-1.3.min.js"></script>





<script type="text/javascript">
	function submitForm() {
		var validator = $("#myForm").validate({
			rules : {
				name : "required",
				surname : "required",
				phone : "required"
			},
			errorElement : "span",
			messages : {
				name : " Enter Name",
				surname : " Enter Surname",
				phone : "Enter Contact No"
			}
		});
		if (validator.form()) { // validation perform
			$('form#myForm').attr({
				action : '#'
			});
			$('form#myForm').submit();
		}
	}
</script>

<script type="text/javascript">
	$(document).ready(
			function() {

				$("#phone").mask("(999) 999-9999");

				$("#phone").on(
						"blur",
						function() {
							var last = $(this).val().substr(
									$(this).val().indexOf("-") + 1);

							if (last.length == 3) {
								var move = $(this).val().substr(
										$(this).val().indexOf("-") - 1, 1);
								var lastfour = move + last;

								var first = $(this).val().substr(0, 9);

								$(this).val(first + '-' + lastfour);
							}
						});

			});
</script>
</head>
<body>

	<form method="post" id="myForm" action="save">

		<table>

			<tr>

				<td><label for="name">Name<em>*</em></label></td>
				<td><input tabindex="1" size="40" type="text" name="name"
					id="name" /></td>
			</tr>

			<tr>
				<td><label for="surname">Surname<em>*</em></label></td>
				<td><input tabindex="2" size="40" type="text" name="surname"
					id="surname" />
				<td>
			</tr>

			<tr>
				<td><label for="phone">Contact Number<em>*</em></label></td>
				<td><input tabindex="3" size="40" type="text" name="phone"
					id="phone" /></td>

			</tr>

			<tr>
				<td>
					<div class="g-recaptcha"
						data-sitekey="6LcePAATAAAAAGPRWgx90814DTjgt5sXnNbV5WaW"></div>
				</td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit" onclick="submitForm();" />
				</td>
				<td><input type="button" value="Geri D�n!"
					onClick="javascript:window.location='/com.infonal'; " /></td>

			</tr>



		</table>


	</form>







	<script
		src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
		async defer></script>
	<script src='https://www.google.com/recaptcha/api.js'></script>

	<script type="text/javascript">
		var onloadCallback = function() {
			grecaptcha.render('html_element', {
				'sitekey' : '6LcfxgITAAAAAKyHeWr86mFvJ-hgbxnN4Qsu-KiR'
			});
		};
	</script>

</body>
</html>