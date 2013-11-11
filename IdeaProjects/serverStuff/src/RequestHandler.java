import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class RequestHandler {

    private final HashMap state;
    private Map headerFields;
    private String httpMethod;
    private String route;
    private String protocol;
    private byte[] body;
    private String head;
    private BufferedReader bufferedReader;
    private String queryString;

    public RequestHandler(BufferedReader bufferedReader, HashMap state) {
        this.bufferedReader = bufferedReader;
        this.state = state;
    }

    public HashMap respondToRequest() throws IOException {
        readHead();
        String request = getHead();
        parseRequest(request);

        if (headerFields.containsKey("Content-Length")) {
            setBody(readBodyAccordingToContentLength());
        }

        HashMap output = new HashMap();

        if (authorizationIsRequired()) {
            output.put("Authorization", headerFields.get("Authorization"));
            handleAuthorization(output);
        }

        String route = getRoute();
        String method = getHttpMethod();
        byte[] body = getBody();
        Router router = new Router();

        byte[] outputMessage = router.respondToRouteRequest(method, route, body);

        HashMap newState = new HashMap();
        newState.put("state", body);
        newState.put("requests", request);

        output.put("message", outputMessage);
        output.put("state", newState);

        return output;
    }

    public void parseRequest(String request) throws UnsupportedEncodingException {
        splitHeadFromBody(request);
        ArrayList<String> splitRequest = splitRequestByLine(head);
        parseHead(splitRequest.get(0));
        splitRequest.remove(0);
        mapFields(splitRequest);
    }

    public void splitHeadFromBody(String request) {
        String[] splitMessage = request.split("\r\n\r\n");
        setHead(splitMessage[0]);
    }

    public void parseHead(String message) throws UnsupportedEncodingException {
        String[] splitHead = message.split("\\s+");
        Arrays.asList(splitHead);
        setHttpMethod(splitHead[0]);
        parseRoute(splitHead[1]);
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

    public void readHead() throws IOException {
        String line;
        String head = "";
        try {
                while(!(line = bufferedReader.readLine()).equals("")) {
                    head += line + "\n";
                }
            }
        catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        setHead(head);
    }

    public String readBody(int bytesToRead) throws IOException {
        char[] charArray = new char[bytesToRead];
        bufferedReader.read(charArray, 0, bytesToRead);

        return String.valueOf(charArray);
    }

    public void parseRoute(String route) throws UnsupportedEncodingException {
        String[] splittedRoute = route.split("\\?");
        setRoute(splittedRoute[0]);
        if (splittedRoute.length > 1) {
            setQueryString(splittedRoute[1]);
            parseQueryString(getQueryString());
        }
    }

    public void parseQueryString(String queryString) throws UnsupportedEncodingException {
        QueryStringParser queryStringParser = new QueryStringParser();
        setBody(queryStringParser.queryStringToSymbolString(queryString).getBytes());
    }

    private byte[] readBodyAccordingToContentLength() throws IOException {
        return (addSpacesAroundEqualsSigns(readBody(Integer.parseInt(headerFields.get("Content-Length").toString())))).getBytes();
    }

    private boolean authorizationIsRequired() {
        return (headerFields.containsKey("Authorization") || ("/logs".equals(getRoute())));
    }

    private boolean credentialsAreValid() {
        return headerFields.get("Authorization").equals("Basic YWRtaW46aHVudGVyMg==");
    }

    private boolean credentialsAreRequired(HashMap output) {
        return (output.get("Authorization") != null && output.get("Authorization").equals("Basic YWRtaW46aHVudGVyMg=="));
    }

    private void handleAuthorization(HashMap output) {
        if (credentialsAreRequired(output)) {
            if(credentialsAreValid()){
                setBody(" 200 OK\r\n\r\nGET /log HTTP/1.1\nPUT /these HTTP/1.1\nHEAD /requests HTTP/1.1".getBytes());
            }
        } else {
            setBody(" 401\r\n\r\nAuthentication required".getBytes());
        }
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

    private void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Map getHeaderFields() {
        return headerFields;
    }

    public void setHeaderFields(Map headerFields) {
        this.headerFields = headerFields;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }

    public HashMap getState() {
        return state;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }
}