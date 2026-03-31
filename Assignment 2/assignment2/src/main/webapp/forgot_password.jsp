<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@page import="com.mycompany.assignment2.dao.UserDAO" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Forgot Password</title>
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
                            <li><a href="login.jsp">Login</a></li>
                        </ul>
                    </nav>
                </div>
            </header>

            <section class="container form-wrap">
                <h1>Recover Password</h1>
                <% String recoveredPass=null; String error=null; if(request.getMethod().equalsIgnoreCase("POST")) {
                    String loginId=request.getParameter("loginId"); String answer=request.getParameter("answer");
                    UserDAO dao=new UserDAO(); recoveredPass=dao.getPassword(loginId, answer); if(recoveredPass==null) {
                    error="Invalid Login ID or Security Answer" ; } } %>

                    <% if(recoveredPass !=null) { %>
                        <div style="border: 1px solid green; padding: 10px; color: green;">
                            <h3>Your Password is: <%= recoveredPass %>
                            </h3>
                            <a href="login.jsp">Login Now</a>
                        </div>
                        <% } else { %>
                            <% if(error !=null) { %>
                                <p style="color:red">
                                    <%= error %>
                                </p>
                                <% } %>
                                    <form method="post">
                                        <input type="text" name="loginId" placeholder="Enter Login ID" required>
                                        <input type="text" name="answer" placeholder="Enter Security Answer" required>
                                        <button type="submit">Recover</button>
                                    </form>
                                    <% } %>
            </section>
        </body>

        </html>