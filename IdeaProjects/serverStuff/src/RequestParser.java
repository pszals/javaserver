import java.util.*;

public class RequestParser {
    private Map headerField;
    private String httpMethod;
    private String route;
    private String protocol;

    public void parseRequest(String request) {
        ArrayList<String> splitRequest = splitRequestByLine(request);
        readHead(splitRequest.get(0));
        splitRequest.remove(0);
        mapFields(splitRequest);

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
        setHeaderField(mappedFields);
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

    public Map getHeaderField() {
        return headerField;
    }

    public void setHeaderField(Map headerField) {
        this.headerField = headerField;
    }
}