<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Expression | Java Web</title>

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
    max-width: 550px;
    background: rgba(255, 255, 255, 0.95);
    padding: 40px;
    border-radius: 22px;
    box-shadow: 0 12px 35px rgba(190, 120, 155, 0.15);
    text-align: center;
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
}

.result {
    margin-top: 30px;
    padding: 25px;
    background: linear-gradient(135deg, #FCE7F3, #F3E8FF);
    border: 1px solid #F1C9DE;
    border-radius: 16px;
}

.number {
    font-size: 30px;
    font-weight: bold;
    color: #A64D7B;
    word-break: break-all;
}

footer {
    margin-top: 30px;
    color: #B69AAA;
    font-size: 12px;
}
    </style>
</head>

<body>
    <main class="container">

        <div class="label">Java Server Pages</div>

        <h1>Random Number Generator</h1>

        <p class="description">
            This page demonstrates how to display a Java expression
            directly inside an HTML document using JSP.
        </p>

        <div class="result">
            <p class="label">Generated Number</p>

            <div class="number">
                <%= Math.random() %>
            </div>
        </div>

        <footer>
            JSP Expression Demonstration
        </footer>

    </main>
</body>
</html>
