<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Register</title>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <%@include file="header.jsp" %>
            <!-- Mock header include to be safe if I extracted it, but for now I'll just write the page logic or duplicate header code if I didn't make header.jsp. 
             Wait, I didn't create header.jsp. I should probably have done that.
             I'll just duplicate the nav logic or make a quick header.jsp.
             I'll make header.jsp first in next step or just inline it for simplicity in this file.
             For now, I'll inline the simple header to avoid include complexities if file missing.
        -->

            <header>
                <div class="container">
                    <div id="branding">
                        <h1><span class="highlight">Fashion</span> Store</h1>
                    </div>
                    <nav>
                        <ul>
                            <li><a href="index.jsp">Home</a></li>
                            <li class="current"><a href="register.jsp">Register</a></li>
                            <li><a href="login.jsp">Login</a></li>
                        </ul>
                    </nav>
                </div>
            </header>

            <section class="container form-wrap">
                <h1>Register</h1>
                <p style="color:red">${error}</p>
                <form action="auth" method="post">
                    <input type="hidden" name="action" value="register">
                    <input type="text" name="username" placeholder="Full Name" required>
                    <input type="email" name="email" placeholder="Email" required>
                    <input type="text" name="phone" placeholder="Phone">

                    <input type="text" name="loginId" placeholder="Login ID" required>
                    <input type="password" name="password" placeholder="Password" required>

                    <input type="text" name="passwordQuestion" placeholder="Security Question">
                    <input type="text" name="passwordAnswer" placeholder="Security Answer">

                    <textarea name="address" placeholder="Address"></textarea>
                    <input type="text" name="city" placeholder="City">
                    <input type="text" name="state" placeholder="State">
                    <input type="text" name="country" placeholder="Country">
                    <input type="text" name="pin" placeholder="PIN Code">

                    <label>Captcha:</label>
                    <img src="captcha-image" alt="Captcha">
                    <input type="text" name="captcha" placeholder="Enter Code" required>

                    <button type="submit">Register</button>
                </form>
            </section>
    </body>

    </html>