<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>LOA</title>
<script type='text/javascript' src='script/jquery-1.11.3.min.js'>
</script>
<script>
function generateOffer(appId){
        alert("successfully requested for offers"+appId);
      }
</script>
<script type="text/javascript">
	$('document').ready(function () {
		$('#txtfname').focus();
		$('#register').hide();

		//change background css when user focus in textbox.
		$('#txtfname').focus(function(){
			$('#txtfname').val('');
			$('#txtfname').attr("style","background-color:skyblue");
		})

		$('#txtlname').focus(function(){
			$('#txtlname').val('');
			$('#txtlname').attr("style","background-color:skyblue");
		})

		$('#txtemail').focus(function(){
			$('#txtemail').val('');
			$('#txtemail').attr("style","background-color:skyblue");
		})

		$('#txtpass').focus(function(){
			$('#register').show(1000);
			$('#txtpass').val('');
			$('#txtpass').attr("style","background-color:skyblue");
		})

		$('#txtconpass').focus(function(){
			$('#txtconpass').val('');
			$('#txtconpass').attr("style","background-color:skyblue");
		})


		//change background css when user lost the focus of textbox

		$('#txtfname').focusout(function(){
			if($('#txtfname').val()==''){

					$('#errmsg1').html("<font color='red'><b>*Field should not be empty</b></font>");
					$('#txtfname').focus();
			}
			else if(!isNaN($('#txtfname').val())){
						$('#errmsg1').html("<font color='red'><b>*Numeric values are not allowed</b></font>");
						$('#txtfname').focus();
					}
					else{
						$('#errmsg1').html('');
						$('#txtfname').attr("style","background-color:white;");
					}
		})

		$('#txtlname').focusout(function(){
			if($('#txtlname').val()==''){

				$('#errmsg2').html("<font color='red'><b>*Field should not be empty</b></font>");
				$('#txtlname').focus();
		}
		else if(!isNaN($('#txtlname').val())){
					$('#errmsg2').html("<font color='red'><b>*Numeric values are not allowed</b></font>");
					$('#txtlname').focus();
				}
				else{
					$('#errmsg2').html('');
					$('#txtlname').attr("style","background-color:white");
				}
		})



		$('#txtemail').focusout(function(){
			if($('#txtemail').val()==''){
				$('#errmsg3').html("<font color='red'><b>*Field should not be empty</b></font>");
				$('#txtemail').focus();
			}
			else {
					str=$('#txtemail').val();
					var index = str.indexOf("@");
					if (index == -1)
					{
						$('#errmsg3').html("<font color='red'><b>*Missing @ in email</b></font>");
						$('#txtemail').focus();
					}
					else
					{
							if(str.indexOf(".")==-1)
							{
								$('#errmsg3').html("<font color='red'><b>*Missing .(dot) in email</b></font>");
								$('#txtemail').focus();
							}
							else{
								if (index != str.lastIndexOf("@"))
								{
									$('#errmsg3').html("<font color='red'><b>*Multiples @ in email</b></font>");
									$('#txtemail').focus();
								}
								else{
									$('#errmsg3').html("<font color='red' face='MV Boli'><b>*Please Note this is your user id</b></font>");
									$('#txtemail').attr("style","background-color:white");
								}
							}
					}
			}

		})

		$('#txtpass').focusout(function(){
			if($('#txtpass').val()==''){
				$('#errmsg4').html("<b><font color='red'>*Field should not be empty</font></b>");
				$('#txtpass').focus();
			}
			else{
				$('#errmsg4').html('');
				$('#txtpass').attr("style","background-color:white");
			}
		})

		$('#txtconpass').focusout(function(){
			if($('#txtpass').val()!=$('#txtconpass').val()){
				$('#errmsg5').html("<font color='red'><b>*Confirm Password not match...</b></font>");
				$('#txtconpass').focus();
			}
			else{
				$('#errmsg5').html('');
				$('#txtconpass').attr("style","background-color:white");
			}
		})

	});
</script>
<style>
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

table{
	background-color: ghostwhite;
	border-radius: 10px;
	box-shadow: 1px 1px black;
	border-color: orange;
}
.btn{
	background-color: #008CBA;
  	font-size: 15px;
  	padding: 14px 40px;
  	background-color: white;
    color: black;
    border: 2px solid skyblue;
}
</style>

</head>
<body>
<form id='reg' action='/loan_application/applications' method="post" >
	<table align='center' cellspacing='5' width='50%' style='margin-top:7%'>
	    <tr><td>Id</td><td>Customer Id</td><td>Pan Number</td><td>Generate Offer</td><td>Cibil Status</td><td>Offer Status</td></tr>
	    <c:forEach var = "app" items = "${application}">
        <tr>
        <td>${app.applicationId}</td>
        <td>${app.customerId}</td>
        <td>${app.panNumber}</td>
        <td><input type = "button" value = "Generate" onclick = "generateOffer(${app.applicationId})"/></td>
        <td>101</td>
        <td>Offered</td>
        </tr>
        </c:forEach>
	</table>
</form>

</body>

</html>