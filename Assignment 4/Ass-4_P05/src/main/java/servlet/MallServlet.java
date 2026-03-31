package servlet;

import ejb.MallFacadeBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mall")
public class MallServlet extends HttpServlet {

    @EJB
    private MallFacadeBean facade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", facade.categories());
        req.setAttribute("suppliers", facade.suppliers());
        req.setAttribute("items", facade.items());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String message;

        if ("addCategory".equals(action)) {
            message = facade.addCategory(req.getParameter("categoryName"));
        } else if ("addSupplier".equals(action)) {
            message = facade.addSupplier(req.getParameter("supplierName"));
        } else if ("addItem".equals(action)) {
            message = facade.addItem(
                    req.getParameter("itemName"),
                    req.getParameter("price"),
                    req.getParameter("categoryId"),
                    req.getParameter("supplierId")
            );
        } else {
            message = "Unknown action";
        }

        req.setAttribute("message", message);
        doGet(req, resp);
    }
}

