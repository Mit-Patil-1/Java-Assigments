<%@page import="com.mycompany.assignment2.dao.CategoryDAO" %>
    <%@page import="com.mycompany.assignment2.dao.ProductDAO" %>
        <%@page import="com.mycompany.assignment2.model.Category" %>
            <%@page import="com.mycompany.assignment2.model.Product" %>
                <%@page import="java.util.List" %>
                    <%@page contentType="text/html" pageEncoding="UTF-8" %>
                        <%@taglib prefix="c" uri="jakarta.tags.core" %>
                            <!DOCTYPE html>
                            <html>

                            <head>
                                <title>Admin Dashboard</title>
                                <link rel="stylesheet" href="../css/style.css">
                            </head>

                            <body>
                                <header>
                                    <div class="container">
                                        <div id="branding">
                                            <h1><span class="highlight">Admin</span> Dashboard</h1>
                                        </div>
                                        <nav>
                                            <ul>
                                                <li><a href="../index.jsp">Home</a></li>
                                                <li class="current"><a href="dashboard.jsp">Dashboard</a></li>
                                                <li><a href="../auth?action=logout">Logout</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </header>

                                <section class="container form-wrap">
                                    <h1>Manage Store</h1>
                                    <p style="color:green">${param.msg}</p>

                                    <div style="float:left; width: 45%; margin-right: 5%;">
                                        <h2>Add Category</h2>
                                        <form action="action" method="post">
                                            <input type="hidden" name="action" value="addCategory">
                                            <input type="text" name="categoryName" placeholder="Category Name" required>
                                            <input type="number" name="parentCategoryId"
                                                placeholder="Parent Category ID (Optional)">
                                            <button type="submit">Add Category</button>
                                        </form>

                                        <h3>Categories</h3>
                                        <ul>
                                            <% CategoryDAO catDAO=new CategoryDAO(); List<Category> cats =
                                                catDAO.getAllCategories();
                                                for(Category c : cats) {
                                                %>
                                                <li>
                                                    <%= c.getCategoryName() %> (ID: <%= c.getCategoryId() %>)
                                                </li>
                                                <% } %>
                                        </ul>
                                    </div>

                                    <div style="float:left; width: 50%;">
                                        <h2>Add Product</h2>
                                        <form action="action" method="post">
                                            <input type="hidden" name="action" value="addProduct">
                                            <input type="text" name="productName" placeholder="Product Name" required>
                                            <input type="text" name="price" placeholder="Price" required>
                                            <input type="text" name="unit" placeholder="Unit (e.g. pcs)" required>
                                            <input type="text" name="discount" placeholder="Discount %" value="0">
                                            <input type="text" name="image" placeholder="Image URL/Path">
                                            <select name="categoryId">
                                                <% for(Category c : cats) { %>
                                                    <option value="<%= c.getCategoryId() %>">
                                                        <%= c.getCategoryName() %>
                                                    </option>
                                                    <% } %>
                                            </select>
                                            <input type="text" name="stock" placeholder="Stock Quantity" required>
                                            <button type="submit">Add Product</button>
                                        </form>
                                    </div>
                                    <div style="clear:both;"></div>

                                    <hr>

                                    <h2>Product List</h2>
                                    <table>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Stock</th>
                                            <th>Action</th>
                                        </tr>
                                        <% ProductDAO prodDAO=new ProductDAO(); List<Product> prods =
                                            prodDAO.getAllProducts();
                                            for(Product p : prods) {
                                            %>
                                            <tr>
                                                <td>
                                                    <%= p.getProductId() %>
                                                </td>
                                                <td>
                                                    <%= p.getProductName() %>
                                                </td>
                                                <td>
                                                    <%= p.getPrice() %>
                                                </td>
                                                <td>
                                                    <%= p.getStock() %>
                                                </td>
                                                <td>
                                                    <form action="action" method="post">
                                                        <input type="hidden" name="action" value="deleteProduct">
                                                        <input type="hidden" name="id" value="<%= p.getProductId() %>">
                                                        <button type="submit"
                                                            style="background:red; padding: 5px;">Delete</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <% } %>
                                    </table>

                                    <hr>
                                    <h2>Reports</h2>
                                    <p><a href="../reports.jsp">View Stock and Visitor Reports</a></p>
                                </section>
                            </body>

                            </html>