package com.mycompany.assignment2.servlet;

import com.mycompany.assignment2.dao.CategoryDAO;
import com.mycompany.assignment2.dao.ProductDAO;
import com.mycompany.assignment2.model.Category;
import com.mycompany.assignment2.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/action"})
public class AdminServlet extends HttpServlet {

    private CategoryDAO categoryDAO = new CategoryDAO();
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("addCategory".equals(action)) {
            addCategory(request, response);
        } else if ("addProduct".equals(action)) {
            addProduct(request, response);
        } else if ("updateProduct".equals(action)) {
            // Implementation left as per requirement "edit"
        } else if ("deleteProduct".equals(action)) {
            deleteProduct(request, response);
        }
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("categoryName");
        String parentId = request.getParameter("parentCategoryId");
        
        Category c = new Category();
        c.setCategoryName(name);
        if (parentId != null && !parentId.isEmpty()) {
            c.setParentCategoryId(Integer.parseInt(parentId));
        }
        categoryDAO.addCategory(c);
        response.sendRedirect("dashboard.jsp?msg=Category Added");
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product p = new Product();
        p.setProductName(request.getParameter("productName"));
        p.setPrice(Double.parseDouble(request.getParameter("price")));
        p.setUnit(request.getParameter("unit"));
        p.setDiscount(Double.parseDouble(request.getParameter("discount")));
        p.setImage(request.getParameter("image")); // In real app, handle file upload
        p.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        
        productDAO.addProduct(p);
        response.sendRedirect("dashboard.jsp?msg=Product Added");
    }
    
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        response.sendRedirect("dashboard.jsp?msg=Product Deleted");
    }
}
