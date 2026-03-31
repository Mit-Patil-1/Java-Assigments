package com.mycompany.assignment2.servlet;

import com.mycompany.assignment2.dao.OrderDAO;
import com.mycompany.assignment2.dao.ProductDAO;
import com.mycompany.assignment2.model.Order;
import com.mycompany.assignment2.model.OrderDetail;
import com.mycompany.assignment2.model.Product;
import com.mycompany.assignment2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<OrderDetail> cart = (List<OrderDetail>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product p = productDAO.getProductById(productId);
            if (p != null) {
                // Check if already in cart
                boolean found = false;
                for (OrderDetail item : cart) {
                    if (item.getProductId() == productId) {
                        item.setQuantity(item.getQuantity() + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    OrderDetail item = new OrderDetail();
                    item.setProductId(p.getProductId());
                    item.setProductName(p.getProductName());
                    item.setProductPrice(p.getPrice());
                    item.setDiscount(p.getDiscount());
                    item.setQuantity(1);
                    cart.add(item);
                }
            }
            response.sendRedirect("shop.jsp?msg=Added to Cart");
            
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int qty = Integer.parseInt(request.getParameter("quantity"));
            Iterator<OrderDetail> it = cart.iterator();
            while (it.hasNext()) {
                OrderDetail item = it.next();
                if (item.getProductId() == productId) {
                    if (qty <= 0) it.remove();
                    else item.setQuantity(qty);
                    break;
                }
            }
            response.sendRedirect("cart.jsp");
            
        } else if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            cart.removeIf(item -> item.getProductId() == productId);
            response.sendRedirect("cart.jsp");
            
        } else if ("checkout".equals(action)) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            if (cart.isEmpty()) {
                response.sendRedirect("cart.jsp?error=Cart is empty");
                return;
            }
            
            double total = 0;
            for (OrderDetail item : cart) {
                double price = item.getProductPrice() - (item.getProductPrice() * item.getDiscount() / 100);
                total += price * item.getQuantity();
            }
            
            Order order = new Order();
            order.setSessionId(session.getId());
            order.setUserId(user.getUserId());
            order.setPaymentMode("COD"); // Simplified
            order.setTax(total * 0.18); // 18% Tax
            order.setTotalAmount(total + order.getTax());
            order.setOrderStatus("CONFIRMED");
            
            int orderId = orderDAO.createOrder(order, cart);
            if (orderId > 0) {
                session.removeAttribute("cart");
                response.sendRedirect("cart.jsp?msg=Order Placed Successfully! Order ID: " + orderId);
            } else {
                response.sendRedirect("cart.jsp?error=Order Failed");
            }
        }
    }
}
