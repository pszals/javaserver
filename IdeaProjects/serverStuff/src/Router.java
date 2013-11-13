import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Router {

    private final ResponseHolder responseHolder = new ResponseHolder();

    public byte[] respondToRouteRequest(String method, String route, byte[] body) throws IOException {
        List<String> routesWithFilesToRead = Arrays.asList("/form", "/file1", "/file1", "/text-file.txt", "/image.jpeg", "/image.gif", "/image.png");
        List<String> unModifiableRoutes = Arrays.asList("/file1", "/file1", "/text-file.txt", "/image.jpeg", "/image.gif", "/image.png");
        byte[] response;
        ResponseBuilder responseBuilder = new ResponseBuilder();

        if (method.equals("GET") && route.equals("/")) {
            response = responseHolder.indexResponse();

        } else if (route.equals("/method_options")) {
            response = responseHolder.methodOptionsResponse();

        } else if (requestModifiesForm(method, route)) {
            response = responseHolder.modifiedFormResponse(body);

        } else if (method.equals("GET") && route.equals("/parameters")) {
            response = responseHolder.parametersResponse(body, responseBuilder);

        } else if (method.equals("GET") && route.equals("/logs")) {
            response = responseHolder.requestLogsResponse(body, responseBuilder);

        } else if (method.equals("GET") && route.equals("/form")) {
            response = responseHolder.getFormResponse(route, responseBuilder);

        } else if (method.equals("GET") && routesWithFilesToRead.contains(route)) {
            response = responseHolder.readFileResponse(route, responseBuilder);

        } else if (method.equals("GET") && route.equals("/redirect")) {
            response = responseHolder.redirectResponse();

        } else if (method.equals("GET") && route.equals("/partial_content.txt")) {
            response = responseHolder.partialContentResponse();

        } else if ((method.equals("PUT") || method.equals("POST")) && unModifiableRoutes.contains(route)) {
            response = responseHolder.methodNotAllowedResponse();
        } else if (method.equals("GET") && route.equals("/ttt")) {
            ArrayList board = new ArrayList();
            board.add(1);
            board.add(2);
            board.add(3);
            board.add(4);
            board.add(5);
            board.add(6);
            board.add(7);
            board.add(8);
            board.add(9);
            response = responseHolder.tttHome(board);

        } else {
            response = responseHolder.notFoundResponse();
        }
        return response;
    }

    private boolean requestModifiesForm(String method, String route) {
        return (method.equals("POST") || (method.equals("PUT"))) && route.equals("/form");
    }
}