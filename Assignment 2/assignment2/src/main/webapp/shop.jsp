<%@page import="com.mycompany.assignment2.dao.ProductDAO" %>
    <%@page import="com.mycompany.assignment2.model.Product" %>
        <%@page import="java.util.List" %>
            <%@page contentType="text/html" pageEncoding="UTF-8" %>
                <%@taglib prefix="c" uri="jakarta.tags.core" %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <title>Shop</title>
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
                                        <li class="current"><a href="shop.jsp">Shop</a></li>
                                        <c:if test="${sessionScope.user == null}">
                                            <li><a href="login.jsp">Login</a></li>
                                            <li><a href="register.jsp">Register</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.user != null}">
                                            <li><a href="profile.jsp">Profile</a></li>
                                            <li><a href="cart.jsp">Cart</a></li>
                                            <li><a href="auth?action=logout">Logout</a></li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </header>

                        <section class="container form-wrap">
                            <h1>Products</h1>
                            <p style="color:green">${param.msg}</p>

                            <% ProductDAO dao=new ProductDAO(); List<Product> products = dao.getAllProducts();
                                request.setAttribute("products", products);
                                %>

                                <div style="overflow: hidden;">
                                    <c:forEach var="p" items="${products}">
                                        <div class="card">
                                            <img src="https://placehold.co/200x200?text=${p.productName}"
                                                alt="${p.productName}"><br>
                                            <h3>${p.productName}</h3>
                                            <p>Category: ${p.categoryName}</p>
                                            <p>Price: $${p.price}</p>
                                            <c:if test="${p.discount > 0}">
                                                <p style="color:red">-${p.discount}% Off</p>
                                            </c:if>
                                            <form action="shop" method="post">
                                                <input type="hidden" name="action" value="add">
                                                <input type="hidden" name="productId" value="${p.productId}">
                                                <button type="submit">Add to Cart</button>
                                            </form>
                                        </div>
                                    </c:forEach>
                                </div>
                        </section>
                    </body>

                    </html>