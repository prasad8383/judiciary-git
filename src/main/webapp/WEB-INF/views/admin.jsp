<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Application Overview</title>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <script>
        function generateOffer(appId){
         var xhttp = new XMLHttpRequest();
           xhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
              alert("ajax called");
             }
           };
           xhttp.open("GET", "generateOffer?applicationId="+appId, true);
           xhttp.send();
           alert("Offer generated successfully");
           document.getElementById(appId).disabled = true;
        }

        function viewOffer(appId){
          var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
              if (this.readyState == 4 && this.status == 200) {
               alert("ajax called");
              }
            };
            xhttp.open("GET", "viewOffer?applicationId="+appId, true);
            xhttp.send();
       }
    </script>

    <style>
.btn{
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
       <b>CDAC LOAN BANK</b> (Taking Banking Technology to Common Man, Your Tech-friendly bank) <font color = "red">*For more information call on us: 8308341837</font>
       </marquee></div>
    <div class="testbox">
      <form action="/">
        <h1>Customer Applications</h1>
        <hr />
        <table cellspacing="5">
          <tr>
            <th>Application Id</th>
            <th>Customer Id</th>
            <th>Customer Name</th>
            <th>Pan Number</th>
            <th>Generate Offer</th>
            <th>View</th>
          </tr>
          <c:forEach var = "app" items = "${application}">
          <tr>
            <td>${app.applicationId}</td>
            <td>${app.customerId}</td>
            <td>${app.customerName}</td>
            <td>${app.panNumber}</td>
            <td><input type = "button" value = "Generate" onclick = "generateOffer(${app.applicationId})" id = "${app.applicationId}" ${app.generateOffer} /></td>
            <td><a href="/loan_application/viewOffer?applicationId=${app.applicationId}"><input type = "button" value = "View Offers" class = "btn"/></a></td>
          </tr>
          </c:forEach>
        </table>
      </form>
    </div>
  </body>
</html>