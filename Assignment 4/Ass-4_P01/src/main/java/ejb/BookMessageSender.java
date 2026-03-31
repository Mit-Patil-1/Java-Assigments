package ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.inject.Inject;

@Stateless
public class BookMessageSender {

    @Resource(lookup = "java:global/jms/bookRequestQueue")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    public void sendRequest(String requestId, long bookId) {
        jmsContext.createProducer().send(queue, requestId + ":" + bookId);
    }
}

