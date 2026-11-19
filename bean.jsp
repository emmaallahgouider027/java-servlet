
<%@ page import="isep.lab1.bean.Person" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Person Information | Java Web</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(135deg, #FFF8F5, #FCE7F3, #F3E8FF);
            color: #594052;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 25px;
        }

        .container {
            width: 100%;
            max-width: 600px;
            background: white;
            padding: 40px;
            border-radius: 22px;
            box-shadow: 0 12px 35px rgba(190, 120, 155, 0.15);
            border: 1px solid #F8DCEB;
        }

        .label {
            color: #BE789B;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 2px;
            font-weight: bold;
        }

        h1 {
            font-size: 27px;
            margin: 15px 0;
            color: #874D70;
        }

        .description {
            color: #91798A;
            line-height: 1.6;
            margin-bottom: 30px;
        }

        .info-card {
            background: #FFF8FB;
            border: 1px solid #F4D9E8;
            border-radius: 14px;
            padding: 18px 22px;
            margin-bottom: 15px;
        }

        .info-title {
            color: #A98299;
            font-size: 12px;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 8px;
        }

        .info-value {
            font-size: 17px;
            font-weight: bold;
            color: #874D70;
            overflow-wrap: anywhere;
        }

        footer {
            margin-top: 25px;
            text-align: center;
            color: #B69AAA;
            font-size: 12px;
        }
    </style>
</head>

<body>

    <main class="container">

        <div class="label">JavaBean Integration</div>

        <h1>Personal Information</h1>

        <p class="description">
            The submitted information has been stored
            and retrieved using the Person JavaBean.
        </p>

        <jsp:useBean id="person"
                     class="isep.lab1.bean.Person"
                     scope="request" />

        <jsp:setProperty name="person" property="*" />

        <div class="info-card">
            <div class="info-title">First Name</div>
            <div class="info-value">
                <jsp:getProperty name="person" property="firstName" />
            </div>
        </div>

        <div class="info-card">
            <div class="info-title">Last Name</div>
            <div class="info-value">
                <jsp:getProperty name="person" property="lastName" />
            </div>
        </div>

        <div class="info-card">
            <div class="info-title">Birth Date</div>
            <div class="info-value">
                <jsp:getProperty name="person" property="birthDate" />
            </div>
        </div>

        <div class="info-card">
            <div class="info-title">Sex</div>
            <div class="info-value">
                <jsp:getProperty name="person" property="sex" />
            </div>
        </div>

        <footer>Person JavaBean Demonstration</footer>

    </main>

</body>
</html>
