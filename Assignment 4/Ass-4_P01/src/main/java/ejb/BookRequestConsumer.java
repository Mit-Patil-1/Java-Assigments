package ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/bookRequestQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class BookRequestConsumer implements MessageListener {

    @EJB
    private BookCatalogBean catalogBean;

    @EJB
    private BookResponseStore responseStore;

    @Override
    public void onMessage(Message message) {
        try {
            String payload = ((TextMessage) message).getText();
            String[] parts = payload.split(":");
            String requestId = parts[0];
            long bookId = Long.parseLong(parts[1]);
            String xml = catalogBean.buildBookXml(bookId);
            responseStore.save(requestId, xml);
        } catch (Exception ignored) {
        }
    }
}

