
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Scriptlet | Java Web</title>

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
    max-width: 650px;
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
    margin: 18px 0;
    color: #874D70;
}

.description {
    color: #91798A;
    line-height: 1.7;
    margin-bottom: 30px;
}

.info-card {
    background: #FFF8FB;
    border: 1px solid #F4D9E8;
    border-radius: 15px;
    padding: 22px;
    margin-bottom: 16px;
    transition: transform 0.2s, box-shadow 0.2s;
}

.info-card:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 18px rgba(190, 120, 155, 0.12);
}

.info-title {
    color: #A98299;
    font-size: 13px;
    margin-bottom: 10px;
    text-transform: uppercase;
    letter-spacing: 1px;
}

.info-value {
    font-size: 17px;
    font-weight: bold;
    color: #A64D7B;
    overflow-wrap: anywhere;
}

footer {
    margin-top: 30px;
    text-align: center;
    color: #B69AAA;
    font-size: 12px;
}
    </style>
</head>

<body>
    <main class="container">

        <div class="label">Java Server Pages</div>

        <h1>Server Information</h1>

        <p class="description">
            This page demonstrates the use of JSP scriptlets
            to execute Java instructions and retrieve information.
        </p>

        <%
            java.util.Date currentDate = new java.util.Date();
            String serverHost = request.getRemoteHost();
        %>

        <section class="info-card">
            <div class="info-title">Current Date and Time</div>

            <div class="info-value">
                <%= currentDate %>
            </div>
        </section>

        <section class="info-card">
            <div class="info-title">Remote Host</div>

            <div class="info-value">
                <%= serverHost %>
            </div>
        </section>

        <footer>
            JSP Scriptlet Demonstration
        </footer>

    </main>
</body>
</html>
