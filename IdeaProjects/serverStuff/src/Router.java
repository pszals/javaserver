import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Router {

    private final ResponseHolder responseHolder = new ResponseHolder();

    public byte[] respondToRouteRequest(String method, String route, byte[] body) throws IOException {
        List<String> routesWithFilesToRead = Arrays.asList("/form", "/file1", "/file1", "/text-file.txt", "/image.jpeg", "/image.gif", "/image.png", "/tictac.css");
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

            ArrayList board;
            ClojureTTTBoardParser parser = new ClojureTTTBoardParser();
            ClojureTTTBoardHolder holder = new ClojureTTTBoardHolder();
            String stringBoard = holder.readBoardStateFile();
            board = parser.convertStringToBoardArray(stringBoard);
            response = responseHolder.tttHome(board);

        } else if (method.equals("POST") && route.equals("/play_game") ) {
            String bodyString = new String(body, "utf-8");
            String lastChar = bodyString.substring(bodyString.length() - 1);
            ClojureTTTGameRunner runner = new ClojureTTTGameRunner();
            runner.runCycle(lastChar);

            response = responseHolder.keepPlayingResponse();

        } else if (method.equals("POST") && route.equals("/reset") ) {
            ClojureTTTBoardHolder holder = new ClojureTTTBoardHolder();
            holder.resetBoard();

            response = responseHolder.keepPlayingResponse();
        }
        else {
            response = responseHolder.notFoundResponse();
        }
        return response;
    }

    private boolean requestModifiesForm(String method, String route) {
        return (method.equals("POST") || (method.equals("PUT"))) && route.equals("/form");
    }
}