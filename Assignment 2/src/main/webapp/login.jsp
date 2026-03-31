<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <header>
            <div class="container">
                <div id="branding">
                    <h1><span class="highlight">Fashion</span> Store</h1>
                </div>
                <nav>
                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="register.jsp">Register</a></li>
                        <li class="current"><a href="login.jsp">Login</a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <section class="container form-wrap">
            <h1>Login</h1>
            <p style="color:red">${error}</p>
            <p style="color:green">${param.msg}</p>
            <form action="auth" method="post">
                <input type="hidden" name="action" value="login">
                <input type="text" name="loginId" placeholder="Login ID" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit">Login</button>
            </form>
            <br>
            <a href="forgot_password.jsp">Forgot Password?</a>
        </section>
    </body>

    </html>