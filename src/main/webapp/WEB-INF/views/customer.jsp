<!DOCTYPE html>
<html>
<head>
	<title></title>
<script type='text/javascript' src='script/jquery-1.11.3.min.js'>
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
<form id='reg' action='/loan_application/create' method="post" >
	<table align='center' cellspacing='5' width='50%' style='margin-top:7%'>
		<tr><td colspan="3" align='center'><b><font color='red' size="5">Loan Application & Bank Details</font></b></td></tr>
		<tr>
			<td align='center'>Customer id</td><td><input type='text' id='txtfname' name = "customerId" readonly="true" value="${customer.customerId}"><center><span id='errmsg1'></center></span></td>
		</tr>
		<tr>
        	<td align='center'>Customer Name</td><td><input type='text' id='txtfname' name = "customerName" readonly="true" value="${customer.customerId}"><center><span id='errmsg1'></center></span></td>
        </tr>
        <tr>
        	<td align='center'>Account Number</td><td><input type='text' id='txtmname' name = "accNo"><center><span id='errmsg01'></center></span></td>
       	</tr>

		<tr>
			<td align='center'>Address</td><td><input type='text' id='txtlname' name = "address"><center><span id='errmsg02'></center></span></td>
		</tr>

		<tr>
             <td align='center'>Bank Name</td><td><input type='text' id='txtcnno' name = "bankName"><center><span id='errmsg2'></center></span></td>
         </tr>

		<tr>
			<td align='center'>Pin Code</td><td><input type='text' id='txtemail' name="pinCode"><center><span id='errmsg3'></center></span></td>
		</tr>

		<tr>
			<td align='center'>Pan No</td><td><input type='text' id='txtpass' name = "panNo"><center><span id='errmsg4'></center></td></span>
		</tr>
         <tr>
            <td align='center'>Income/Year</td><td><input type='text' id='txtpass' name = "annualIncome"><center><span id='errmsg4'></center></td></span>
         </tr>
         <tr>
            <td align='center'>Maximum Loan Amount</td><td><input type='text' id='txtpass' name = "requestedLoanAmt"><center><span id='errmsg4'></center></td></span>
         </tr>
		<tr>
			<td colspan='2' align='right'><input type="submit" id='register' class='btn' value="Create Application">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value='Clear' class='btn'><td></td></td>
		</tr>
	</table>
</form>
</body>
</html>