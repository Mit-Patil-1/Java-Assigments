package servlet;

import ejb.CurrencyConverterBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/convert")
public class CurrencyServlet extends HttpServlet {

    @EJB
    private CurrencyConverterBean bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        double amount = Double.parseDouble(req.getParameter("amount"));

        if (!bean.hasRate(from, to)) {
            req.setAttribute("message", "Rate not found for " + from + " -> " + to);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        double converted = bean.convert(from, to, amount);
        req.setAttribute("result", converted);
        req.setAttribute("message", amount + " " + from.toUpperCase() + " = " + converted + " " + to.toUpperCase());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

