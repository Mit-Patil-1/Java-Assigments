package servlet;

import ejb.BookResponseStore;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book-response")
public class BookResponseServlet extends HttpServlet {

    @EJB
    private BookResponseStore store;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestId = req.getParameter("requestId");
        String xml = store.get(requestId);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/xml");
        if (xml == null) {
            resp.getWriter().write("<book-response><status>PENDING</status></book-response>");
            return;
        }
        resp.getWriter().write(xml);
    }
}

