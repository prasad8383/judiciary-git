<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Application Overview</title>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <script>
    function myFunction() {
      alert("Successfully Requested for offer");
    }
    </script>
    <style>
    #btn{
                width: 150px;
                padding: 10px;
                border: none;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                background-color: #095484;
                font-size: 16px;
                color: #fff;
                cursor: pointer;

          }
      html, body {
      min-height: 100%;
      }
      body, div, form, input, p {
      padding: 0;
      margin: 0;
      outline: none;
      font-family: Roboto, Arial, sans-serif;
      font-size: 14px;
      color: #666;
      line-height: 22px;
      }
      h1 {
      font-weight: 400;
      }
      h4 {
      margin: 15px 0 4px;
      }
      .testbox {
      display: flex;
      justify-content: center;
      align-items: center;
      height: inherit;
      padding: 3px;
      }
      form {
      width: 100%;
      padding: 20px;
      background: #fff;
      box-shadow: 0 2px 5px #ccc;
      }
      input {
      width: calc(100% - 10px);
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 3px;
      vertical-align: middle;
      }
      .email {
      display: block;
      width: 45%;
      }
      input:hover, textarea:hover {
      outline: none;
      border: 1px solid #095484;
      }
      th, td {
      width: 15%;
      padding: 15px 0;
      border-bottom: 1px solid #ccc;
      text-align: center;
      vertical-align: unset;
      line-height: 18px;
      font-weight: 400;
      word-break: break-all;
      }
      .first-col {
      width: 16%;
      text-align: left;
      }
      table {
      width: 100%;
      }
      textarea {
      width: calc(100% - 6px);
      }
      .btn-block {
      margin-top: 20px;
      text-align: center;
      }
      button {
      width: 150px;
      padding: 10px;
      border: none;
      -webkit-border-radius: 5px;
      -moz-border-radius: 5px;
      border-radius: 5px;
      background-color: #095484;
      font-size: 16px;
      color: #fff;
      cursor: pointer;
      }
      button:hover {
      background-color: #0666a3;
      }
      @media (min-width: 568px) {
      th, td {
      word-break: keep-all;
      }
      }
    </style>
  </head>
  <body>
  <div align = "right"><a href="/loan_application/home"><button>Logout</button></a></div>
  <div><marquee width="60%" direction="left" height="50px">
         <b>CDAC LOAN BANK</b> (Taking Banking Technology to Common Man, Your Tech-friendly bank)   <font color = "red">*For more information call on us: 9604889377</font>
         </marquee></div>
    <div class="testbox">
      <form action="/">
        <h1>Application Overview</h1>
        <hr />
        <h3>Personal Details</h3>
        <table>
          <tr>
            <th class="first-col"></th>
            <th>Customer Ref No</th>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Mobile Number</th>
          </tr>
          <tr>
            <td class="first-col"></td>
            <td>${customerId}</td>
            <td>${firstName}</td>
            <td>${middleName}</td>
            <td>${lastName}</td>
            <td>${email}</td>
            <td>${mobileNumber}</td>
          </tr>
        </table>
<h3>Appliction Details</h3>
        <table>
          <tr>
            <th class="first-col"></th>
            <th>Loan Appl Id</th>
            <th>Pan Number</th>
            <th>Bank Name</th>
            <th>Account Number</th>
            <th>Annual Income</th>
            <th>Created Date</th>
          </tr>
          <tr>
            <td class="first-col"></td>
            <td>${applicationNumber}</td>
            <td>${pan}</td>
            <td>${bankName}</td>
            <td>${accountNumber}</td>
            <td>${annualIncome}</td>
           <td>${createdDate}</td>
          </tr>
        </table>

<h3>Loan Details</h3>
        <table>
          <tr>
            <th class="first-col"></th>
            <th>Loan Type</th>
            <th>Loan Amount</th>
            <th>Interest Rate</th>
            <th>Interest Amount</th>
            <th>Total Loan Amount</th>
           <th>Offer Status</th>


          </tr>
          <tr>
            <td class="first-col"></td>
            <td>${loanType}</td>
            <td>${loanAmount}</td>
            <td>${interestRate}</td>
            <td>${interestAmount}</td>
           <td>${totalLoanAmount}</td>
           <td>${offerStatus}</td>
          </tr>
        </table>
          <div align = "right"><a href="/loan_application/loanapplicationpage?userId=${customer.userCredentialId}"><input type = "button" value = "Create Application" ${btnFlag} id = "btn"/></a></div>
          <div align = "left"><a href="/loan_application/back"><input type="button" id = "btn" value = "Back" ${btnVal}/></a></div>
          <div align ="right"><input type = "button" value = "Generate Offer" id = "btn" onclick ="myFunction()" ${btnG}/></div>
        </div>
      </form>
    </div>
  </body>
</html>