<%@page import="com.mycompany.assignment2.dao.ProductDAO" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib prefix="c" uri="jakarta.tags.core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Bill</title>
                <link rel="stylesheet" href="css/style.css">
            </head>

            <body>
                <header>
                    <div class="container d-flex justify-content-between align-items-center">
                        <h1>Fashion Store Bill</h1>
                        <button onclick="window.print()">Print</button>
                    </div>
                </header>

                <section class="container form-wrap">
                    <c:if test="${param.orderId != null}">
                        <h2>Order Invoice #${param.orderId}</h2>
                        <p>Date: <%= new java.util.Date() %>
                        </p>
                        <hr>
                        <!-- Retrieve details logic would go here, for now just static confirmation as cart is cleared -->
                        <p>Thank you for your purchase.</p>
                        <p>Your order has been recorded in our system.</p>
                        <a href="index.jsp">Continue Shopping</a>
                    </c:if>
                    <c:if test="${param.orderId == null}">
                        <p>Invalid Order</p>
                    </c:if>
                </section>
            </body>

            </html>