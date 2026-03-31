<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Fashion Store - Home</title>
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
                        <li class="current"><a href="index.jsp">Home</a></li>
                        <li><a href="shop.jsp">Shop</a></li>
                        <c:if test="${sessionScope.user == null}">
                            <li><a href="login.jsp">Login</a></li>
                            <li><a href="register.jsp">Register</a></li>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <li><a href="profile.jsp">Profile</a></li>
                            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                                <li><a href="admin/dashboard.jsp">Admin</a></li>
                            </c:if>
                            <li><a href="cart.jsp">Cart</a></li>
                            <li><a href="auth?action=logout">Logout</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </header>

        <section class="showcase">
            <div class="container">
                <h1>Welcome to Fashion Store</h1>
                <p>Trending fashion for everyone</p>
                <a href="shop.jsp"><button>Shop Now</button></a>
            </div>
        </section>

        <footer>
            <p>Fashion Store &copy; 2026</p>
        </footer>
    </body>
</html>
