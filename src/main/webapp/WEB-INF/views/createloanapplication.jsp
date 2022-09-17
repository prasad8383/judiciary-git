<!DOCTYPE html>
<html>
<head>
	<title></title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js">
 </script>
 <script type="text/javascript" language="javascript">
 $(document).ready(function() {
     	$('#txtAccNo').focusout(function() {
            accNo = $(this).val();
            var filter = /^\d*(?:\.\d{1,2})?$/;
            if (filter.test(accNo)) {
             	if(accNo.length != 10){
             	    alert("Please enter valid account number");
             	}
            }
        })

        $('#txtincome').focusout(function() {
            if(isNaN(document.getElementById('txtincome').value)){
                alert("enter amount in digits only");
            }
        })
        $('#txtreqamt').focusout(function() {
                    if(isNaN(document.getElementById('txtincome').value)){
                        alert("enter amount in digits only");
                    }
        })
        $('#txtpin').focusout(function() {
                    if(isNaN(document.getElementById('txtincome').value)){
                        alert("enter valid pin");
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
			<td align='center'>Customer id</td><td><input type='text' id='txtcustid' name = "customerId" readonly="true" value="${customer.customerId}"><center><span id='errmsg1'></center></span></td>
		</tr>
		<tr>
        	<td align='center'>Customer Name</td><td><input type='text' id='txtname' name = "customerfName" readonly="true" value="${customer.fname}&nbsp;&nbsp;${customer.lanme}"><center><span id='errmsg1'></center></span>
        	</td>
        </tr>
        <tr>
        	<td align='center'>Account Number</td><td><input type='text' id='txtAccNo' name = "accNo" required><center><span id='errmsg01'></center></span></td>
       	</tr>

		<tr>
			<td align='center'>Address</td><td><input type='text' id='txtadd' name = "address" required><center><span id='errmsg02'></center></span></td>
		</tr>

		<tr>
             <td align='center'>Bank Name</td><td><input type='text' id='txtbnkname' name = "bankName" required><center><span id='errmsg2'></center></span></td>
         </tr>

		<tr>
			<td align='center'>Pin Code</td><td><input type='text' id='txtpin' name="pinCode" required><center><span id='errmsg3'></center></span></td>
		</tr>

		<tr>
			<td align='center'>Pan No</td><td><input type='text' id='txtpan' name = "panNo" required><center><span id='errmsg4'></center></td></span>
		</tr>
         <tr>
            <td align='center'>Income/Year</td><td><input type='text' id='txtincome' name = "annualIncome" required><center><span id='errmsg4'></center></td></span>
         </tr>
         <tr>
            <td align='center'>Maximum Loan Amount</td><td><input type='text' id='txtreqamt' name = "requestedLoanAmt" required><center><span id='errmsg4'></center></td></span>
         </tr>
		<tr>
			<td colspan='2' align='right'><input type="submit" id='register' class='btn' value="Create Application">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value='Clear' class='btn'><td></td></td>
		</tr>
	</table>
</form>
</body>
</html>