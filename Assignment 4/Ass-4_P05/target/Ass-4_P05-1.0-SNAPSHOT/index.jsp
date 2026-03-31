<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="entity.Category"%>
<%@page import="entity.Supplier"%>
<%@page import="entity.MallItem"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ass-4_P05 Shopping Mall Facade</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>Shopping Mall Data Operations (Session Facade)</h2>

<p><b><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %></b></p>

<form method="post" action="mall">
    <input type="hidden" name="action" value="addCategory"/>
    <label>Category Name:</label>
    <input type="text" name="categoryName" required/>
    <button type="submit">Add Category</button>
</form>

<form method="post" action="mall" style="margin-top:8px;">
    <input type="hidden" name="action" value="addSupplier"/>
    <label>Supplier Name:</label>
    <input type="text" name="supplierName" required/>
    <button type="submit">Add Supplier</button>
</form>

<form method="post" action="mall" style="margin-top:8px;">
    <input type="hidden" name="action" value="addItem"/>
    <label>Item Name:</label>
    <input type="text" name="itemName" required/>
    <label>Price:</label>
    <input type="number" step="0.01" name="price" required/>
    <label>Category ID:</label>
    <input type="number" name="categoryId" required/>
    <label>Supplier ID:</label>
    <input type="number" name="supplierId" required/>
    <button type="submit">Add Item</button>
</form>

<h3>Categories</h3>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    if (categories != null) {
        for (Category c : categories) {
%>
<p><%= c.getId() %> - <%= c.getCategoryName() %></p>
<%
        }
    }
%>

<h3>Suppliers</h3>
<%
    List<Supplier> suppliers = (List<Supplier>) request.getAttribute("suppliers");
    if (suppliers != null) {
        for (Supplier s : suppliers) {
%>
<p><%= s.getId() %> - <%= s.getSupplierName() %></p>
<%
        }
    }
%>

<h3>Items</h3>
<%
    List<MallItem> items = (List<MallItem>) request.getAttribute("items");
    if (items != null) {
        for (MallItem i : items) {
%>
<p><%= i.getId() %> - <%= i.getItemName() %> - <%= i.getPrice() %> | <%= i.getCategory().getCategoryName() %> | <%= i.getSupplier().getSupplierName() %></p>
<%
        }
    }
%>
</body>
</html>

