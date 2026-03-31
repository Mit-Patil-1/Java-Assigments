<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="ejb.ShoppingCartBean.CartLine"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ass-4_P03 Computer Store Cart</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>Minimal Computer Store Shopping Cart</h2>
<form method="post" action="cart">
    <input type="hidden" name="action" value="add"/>
    <label>Item ID:</label>
    <input type="number" name="itemId" required/>
    <label>Quantity:</label>
    <input type="number" name="qty" min="1" required/>
    <button type="submit">Add to Cart</button>
</form>

<form method="post" action="cart" style="margin-top:10px;">
    <input type="hidden" name="action" value="checkout"/>
    <button type="submit">Checkout</button>
</form>

<%
    Object msg = request.getAttribute("message");
    if (msg != null) {
%>
<p><b><%= msg %></b></p>
<%
    }
    List<CartLine> lines = (List<CartLine>) request.getAttribute("lines");
    if (lines != null && !lines.isEmpty()) {
%>
<table border="1" cellpadding="6">
    <tr><th>Item</th><th>Qty</th><th>Unit Price</th><th>Line Total</th></tr>
    <% for (CartLine line : lines) { %>
    <tr>
        <td><%= line.getItemName() %></td>
        <td><%= line.getQuantity() %></td>
        <td><%= line.getUnitPrice() %></td>
        <td><%= line.getLineTotal() %></td>
    </tr>
    <% } %>
</table>
<p><b>Grand Total: <%= request.getAttribute("total") %></b></p>
<%
    }
%>
</body>
</html>

