import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Server {
    public String request = "";

    public void start() throws IOException {

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
                    header += line
                    + "\r\n";
                }
                setRequest(header);
                request = getRequest();
                RequestParser requestParser = new RequestParser();
                requestParser.parseRequest(request);
                String route = requestParser.getRoute();
                String method = requestParser.getHttpMethod();
                String body = requestParser.getBody();
                Router router = new Router();
                String outputMessage = router.respondToRouteRequest(method, route, body="", requestParser);
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

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
