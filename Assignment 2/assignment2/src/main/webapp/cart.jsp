<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Shopping Cart</title>
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
                            <li><a href="shop.jsp">Shop</a></li>
                            <c:if test="${sessionScope.user != null}">
                                <li><a href="profile.jsp">Profile</a></li>
                                <li class="current"><a href="cart.jsp">Cart</a></li>
                                <li><a href="auth?action=logout">Logout</a></li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </header>

            <section class="container form-wrap">
                <h1>Your Cart</h1>
                <p style="color:red">${param.error}</p>
                <p style="color:green">${param.msg}</p>

                <c:if test="${sessionScope.cart == null || sessionScope.cart.size() == 0}">
                    <p>Your cart is empty. <a href="shop.jsp">Go Shopping</a></p>
                </c:if>

                <c:if test="${sessionScope.cart != null && sessionScope.cart.size() > 0}">
                    <table>
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Discount</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="grandTotal" value="0" />
                            <c:forEach var="item" items="${sessionScope.cart}">
                                <c:set var="finalPrice"
                                    value="${item.productPrice - (item.productPrice * item.discount / 100)}" />
                                <c:set var="lineTotal" value="${finalPrice * item.quantity}" />
                                <c:set var="grandTotal" value="${grandTotal + lineTotal}" />
                                <tr>
                                    <td>${item.productName}</td>
                                    <td>${item.productPrice}</td>
                                    <td>${item.discount}%</td>
                                    <td>
                                        <form action="shop" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="productId" value="${item.productId}">
                                            <input type="number" name="quantity" value="${item.quantity}"
                                                style="width: 50px;">
                                            <button type="submit">Update</button>
                                        </form>
                                    </td>
                                    <td>${lineTotal}</td>
                                    <td>
                                        <form action="shop" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="productId" value="${item.productId}">
                                            <button type="submit" style="background:red;">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h3>Total: $${grandTotal}</h3>
                    <form action="shop" method="post">
                        <input type="hidden" name="action" value="checkout">
                        <button type="submit" style="width: 100%; font-size: 20px;">Checkout / Generate Bill</button>
                    </form>
                </c:if>
            </section>
        </body>

        </html>