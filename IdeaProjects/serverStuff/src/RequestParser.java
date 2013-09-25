import java.util.*;

public class RequestParser {
    private Map headerFields;
    private String httpMethod;
    private String route;
    private String protocol;
    private String body;
    private String head;

    public void parseRequest(String request) {
        splitHeadFromBody(request);
        ArrayList<String> splitRequest = splitRequestByLine(head);
        readHead(splitRequest.get(0));
        splitRequest.remove(0);
        mapFields(splitRequest);
    }

    public void splitHeadFromBody(String request) {
        String[] splitMessage = request.split("\r\n\r\n");
        setHead(splitMessage[0]);
        if (splitMessage.length > 1) {
            String rawBody = splitMessage[1];
            setBody(addSpacesAroundEqualsSigns(rawBody));
        }
    }

    public void readHead(String message) {
        String[] splitHead = message.split("\\s+");
        Arrays.asList(splitHead);
        setHttpMethod(splitHead[0]);
        setRoute(splitHead[1]);
        setProtocol(splitHead[2]);
    }

    public ArrayList<String> splitRequestByLine(String requestHead) {
        return new ArrayList<String>(Arrays.asList(requestHead.split("\n")));
    }

    public void mapFields(ArrayList<String> fields) {
        Map mappedFields = new HashMap();
        for (String message : fields) {
            String[] splitField = message.split(":\\s+");
            mappedFields.put(splitField[0], splitField[1]);
        }
        setHeaderFields(mappedFields);
    }

    public String addSpacesAroundEqualsSigns(String body) {
        return body.replaceAll("=", " = ");
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Map getHeaderFields() {
        return headerFields;
    }

    public void setHeaderFields(Map headerFields) {
        this.headerFields = headerFields;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }
}