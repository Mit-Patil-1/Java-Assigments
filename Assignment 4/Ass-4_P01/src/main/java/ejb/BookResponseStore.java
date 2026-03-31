package ejb;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class BookResponseStore {

    private final Map<String, String> xmlByRequestId = new ConcurrentHashMap<>();

    public void save(String requestId, String xml) {
        xmlByRequestId.put(requestId, xml);
    }

    public String get(String requestId) {
        return xmlByRequestId.get(requestId);
    }
}

