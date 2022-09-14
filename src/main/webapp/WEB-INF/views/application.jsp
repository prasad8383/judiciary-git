<!DOCTYPE html>
<html>
<head>
	<title></title>
	<script type='text/javascript' src='script/jquery-1.11.3.min.js'>
    </script>
    <script>
      function myFunction(){
        var btn = document.getElementById("btn");
        btn.value = "Offer Requested";
        alert("successfully requested for offers");
      }
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
	<table align='center' cellspacing='5' width='50%' style='margin-top:7%'>
		<tr><td colspan="4" align='center'><b><font color='red' size="5">Customer Id : ${customerId}</font></b></td></tr>
        <tr><td align='center'><b><font color='red' size="5">ApplicationId</font></b></td>
            <td align='center'><b><font color='red' size="5">Pan Number</font></b></td>
            <td align='center'><b><font color='red' size="5">Annual Income</font></b></td>
            <td align='center'><b><font color='red' size="5">Created Date</font></b></td>
        </tr>
        <tr><td align='center'><b><font color='red' size="5">${applicationId}</font></b></td>
                    <td align='center'><b><font color='red' size="5">${panNumber}</font></b></td>
                    <td align='center'><b><font color='red' size="5">${annualIncome}</font></b></td>
                    <td align='center'><b><font color='red' size="5">${createDate}</font></b></td>
                </tr>
        <tr><td colspan="4" align='center'><b><font color='red' size="5"><input type= "button" id = "btn" onclick="myFunction()" value = "Generate Offer"/></font></b></td></tr>
</table>
</body>
</html>