<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Person Profile - Young</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px;
            background: linear-gradient(135deg, #fff8f5, #fce7f3, #f3e8ff);
        }

        .card {
            width: 100%;
            max-width: 550px;
            background: white;
            padding: 40px;
            border-radius: 22px;
            box-shadow: 0 10px 35px rgba(167, 108, 145, 0.15);
        }

        .header {
            text-align: center;
            margin-bottom: 30px;
        }

        .icon {
            width: 70px;
            height: 70px;
            margin: auto;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #f3e8ff;
            border-radius: 50%;
            font-size: 30px;
        }

        h1 {
            color: #68458c;
            margin-top: 18px;
            margin-bottom: 10px;
            font-size: 27px;
        }

        .subtitle {
            color: #9b8ab0;
            font-size: 14px;
        }

        .badge {
            display: inline-block;
            margin-top: 18px;
            padding: 8px 16px;
            border-radius: 30px;
            background: #f3e8ff;
            color: #8062a8;
            font-size: 13px;
            font-weight: bold;
        }

        .info {
            margin-top: 25px;
        }

        .info-item {
            background: #fcfaff;
            border: 1px solid #e8ddf4;
            border-radius: 12px;
            padding: 16px;
            margin-bottom: 13px;
        }

        .label {
            font-size: 12px;
            color: #a18dbb;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 7px;
        }

        .value {
            font-size: 16px;
            color: #594052;
            font-weight: bold;
        }

        .footer {
            text-align: center;
            margin-top: 25px;
            color: #b39aaf;
            font-size: 12px;
        }

        .back-button {
            display: block;
            width: 100%;
            margin-top: 20px;
            padding: 13px;
            text-align: center;
            text-decoration: none;
            border-radius: 10px;
            background: #a78bca;
            color: white;
            font-weight: bold;
            transition: 0.3s;
        }

        .back-button:hover {
            background: #8c70b4;
        }
    </style>
</head>

<body>

<jsp:useBean id="person" type="lab.mvc.model.Person" scope="request" />

<div class="card">

    <div class="header">
        <div class="icon">✨</div>

        <h1>Person Profile</h1>

        <p class="subtitle">
            Personal information successfully received
        </p>

        <div class="badge">
            Born in 1990 or later
        </div>
    </div>

    <div class="info">

        <div class="info-item">
            <div class="label">First Name</div>
            <div class="value">
                <jsp:getProperty name="person" property="firstName"/>
            </div>
        </div>

        <div class="info-item">
            <div class="label">Last Name</div>
            <div class="value">
                <jsp:getProperty name="person" property="lastName"/>
            </div>
        </div>

        <div class="info-item">
            <div class="label">Birth Day</div>
            <div class="value">
                <jsp:getProperty name="person" property="birthDay"/>
            </div>
        </div>

        <div class="info-item">
            <div class="label">Sex</div>
            <div class="value">
                <jsp:getProperty name="person" property="sex"/>
            </div>
        </div>

    </div>

    <a href="<%= request.getContextPath() %>/index.html" class="back-button">
        Back to Form
    </a>

    <div class="footer">
        MVC Architecture — Young Person View
    </div>

</div>

</body>
</html>