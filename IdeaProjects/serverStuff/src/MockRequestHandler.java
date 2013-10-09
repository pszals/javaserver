import java.util.HashMap;

public class MockRequestHandler {
    public HashMap respondToRequest() {
        HashMap output = new HashMap();
        output.put("state", "state".getBytes());
        output.put("message", "message".getBytes());
        return output;
    }
}
