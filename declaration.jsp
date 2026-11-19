
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Declaration | Java Web</title>

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
    margin: 18px 0;
    color: #874D70;
}

.description {
    color: #91798A;
    line-height: 1.7;
    margin-bottom: 25px;
}

.list-card {
    background: #FFF8FB;
    border: 1px solid #F4D9E8;
    border-radius: 16px;
    padding: 25px;
}

.list-title {
    font-size: 15px;
    font-weight: bold;
    margin-bottom: 20px;
    color: #A64D7B;
}

ul {
    list-style: none;
    padding: 0;
    margin: 0;
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 12px;
}

li {
    background: white;
    border: 1px solid #F1D5E5;
    border-radius: 10px;
    padding: 14px;
    text-align: center;
    font-size: 18px;
    font-weight: bold;
    color: #874D70;
    transition: all 0.2s;
}

li:hover {
    background: #FCE7F3;
    transform: translateY(-2px);
}

footer {
    margin-top: 25px;
    text-align: center;
    color: #B69AAA;
    font-size: 12px;
}

@media (max-width: 400px) {
    ul {
        grid-template-columns: 1fr;
    }
}
    </style>
</head>

<body>
    <main class="container">

        <div class="label">Java Server Pages</div>

        <h1>Random Number List</h1>

        <p class="description">
            This page demonstrates JSP declarations by defining
            a class-level variable and a Java method.
        </p>

        <%!
            private int numEntries = 10;

            private int randomInt(int range) {
                return 1 + ((int)(Math.random() * range));
            }
        %>

        <section class="list-card">

            <div class="list-title">
                Generated Numbers (1 - 100)
            </div>

            <ul>
                <%
                    for (int i = 0; i < numEntries; i++) {
                        out.println("<li>" + randomInt(100) + "</li>");
                    }
                %>
            </ul>

        </section>

        <footer>
            JSP Declaration Demonstration
        </footer>

    </main>
</body>
</html>
