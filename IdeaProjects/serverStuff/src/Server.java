import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

    public void start() throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5000.");
            System.exit(1);
        }

        HashMap state = null;
        Socket clientSocket = null;
        try {
            while (true) {
                clientSocket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                RequestParser requestParser = new RequestParser(reader, state);

                HashMap response = requestParser.respondToRequest();

                state = (HashMap) response.get("state");

                byte[] outputMessage = (byte[]) response.get("message");

                OutputStream out = clientSocket.getOutputStream();
                out.write(outputMessage);

                out.close();
            }
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        clientSocket.close();
        serverSocket.close();
    }
}