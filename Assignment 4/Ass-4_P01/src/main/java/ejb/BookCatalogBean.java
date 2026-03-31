package ejb;

import entity.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class BookCatalogBean {

    @PersistenceContext(unitName = "ass4p01PU")
    private EntityManager em;

    public String buildBookXml(long bookId) {
        Book book = em.find(Book.class, bookId);
        if (book == null) {
            return "<book-response><status>NOT_FOUND</status><book-id>" + bookId + "</book-id></book-response>";
        }

        return "<book-response>"
                + "<status>OK</status>"
                + "<book>"
                + "<id>" + book.getId() + "</id>"
                + "<title>" + escape(book.getTitle()) + "</title>"
                + "<author>" + escape(book.getAuthor()) + "</author>"
                + "<price>" + book.getPrice() + "</price>"
                + "</book>"
                + "</book-response>";
    }

    private String escape(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}

