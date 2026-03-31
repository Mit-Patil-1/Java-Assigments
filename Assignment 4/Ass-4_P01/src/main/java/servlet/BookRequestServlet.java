package servlet;

import ejb.BookMessageSender;
import jakarta.ejb.EJB;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@JMSDestinationDefinition(
        name = "java:global/jms/bookRequestQueue",
        interfaceName = "jakarta.jms.Queue",
        destinationName = "BookRequestQueue"
)
@WebServlet("/book-request")
public class BookRequestServlet extends HttpServlet {

    @EJB
    private BookMessageSender sender;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("bookId"));
        String requestId = UUID.randomUUID().toString();
        sender.sendRequest(requestId, bookId);
        resp.sendRedirect(req.getContextPath() + "/index.jsp?requestId=" + requestId);
    }
}

