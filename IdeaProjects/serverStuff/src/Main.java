import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Main {
    private static String request = "";

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        String line = null;
        String header = "";
        try {
            while (true) {
                clientSocket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                setRequest("");
                while(!(line = reader.readLine()).equals("")) {
                    header += line;
                }
                setRequest(header);
                request = getRequest();
                RequestParser requestParser = new RequestParser();
                requestParser.parseRequest(request);
                String route = requestParser.getRoute();
                String method = requestParser.getHttpMethod();
                Router router = new Router();
                String outputMessage = router.respondToRouteRequest(method, route);
                OutputStream out = clientSocket.getOutputStream();
                out.write(outputMessage.getBytes(Charset.forName("utf-8")));
                out.close();
                header = "";
            }
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        clientSocket.close();
        serverSocket.close();
    }

    public static String getRequest() {
        return request;
    }

    public static void setRequest(String request) {
        Main.request = request;
    }
}
