package servlet;

import ejb.ShoppingCartBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @EJB
    private ShoppingCartBean cartBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lines", cartBean.getLines());
        req.setAttribute("total", cartBean.getGrandTotal());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            long itemId = Long.parseLong(req.getParameter("itemId"));
            int quantity = Integer.parseInt(req.getParameter("qty"));
            cartBean.addItem(itemId, quantity);
        } else if ("checkout".equals(action)) {
            Long orderId = cartBean.checkout();
            req.setAttribute("message", orderId == null ? "Cart is empty" : "Order placed. Order ID: " + orderId);
        }

        doGet(req, resp);
    }
}

