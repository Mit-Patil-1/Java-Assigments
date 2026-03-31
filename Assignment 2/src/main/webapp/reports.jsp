<%@page import="com.mycompany.assignment2.dao.StatsDAO" %>
    <%@page import="com.mycompany.assignment2.dao.ProductDAO" %>
        <%@page import="com.mycompany.assignment2.model.Product" %>
            <%@page import="java.util.List" %>
                <%@page import="java.util.Map" %>
                    <%@page contentType="text/html" pageEncoding="UTF-8" %>
                        <!DOCTYPE html>
                        <html>

                        <head>
                            <title>Reports</title>
                            <link rel="stylesheet" href="css/style.css">
                        </head>

                        <body>
                            <header>
                                <div class="container">
                                    <div id="branding">
                                        <h1><span class="highlight">Admin</span> Reports</h1>
                                    </div>
                                    <nav>
                                        <ul>
                                            <li><a href="index.jsp">Home</a></li>
                                            <li><a href="admin/dashboard.jsp">Dashboard</a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </header>

                            <section class="container form-wrap">
                                <h1>Stock Report</h1>
                                <table>
                                    <tr>
                                        <th>Product</th>
                                        <th>Stock</th>
                                    </tr>
                                    <% ProductDAO productDAO=new ProductDAO(); List<Product> products =
                                        productDAO.getAllProducts();
                                        for(Product p : products) {
                                        %>
                                        <tr>
                                            <td>
                                                <%= p.getProductName() %>
                                            </td>
                                            <td>
                                                <%= p.getStock() %>
                                            </td>
                                        </tr>
                                        <% } %>
                                </table>

                                <hr>

                                <h1>Frequent Visitors (>5 visits)</h1>
                                <table>
                                    <tr>
                                        <th>IP Address / Client</th>
                                        <th>Visit Count</th>
                                    </tr>
                                    <% StatsDAO statsDAO=new StatsDAO(); List<Map<String, Object>> visitors =
                                        statsDAO.getFrequentVisitors();
                                        for(Map<String, Object> v : visitors) {
                                            %>
                                            <tr>
                                                <td>
                                                    <%= v.get("ip_address") %>
                                                </td>
                                                <td>
                                                    <%= v.get("visit_count") %>
                                                </td>
                                            </tr>
                                            <% } %>
                                </table>
                            </section>
                        </body>

                        </html>