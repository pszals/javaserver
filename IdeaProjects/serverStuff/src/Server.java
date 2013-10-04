import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    HashMap persistentState;

    public void start() throws IOException {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5000.");
            System.exit(1);
        }

        try {
            while (true) {
                respondToRequest(serverSocket, persistentState);
            }
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        clientSocket.close();
        serverSocket.close();
    }

    private void respondToRequest(ServerSocket serverSocket, HashMap persistentState) throws IOException {
        Socket clientSocket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        RequestHandler requestHandler = new RequestHandler(reader, persistentState);
        HashMap response = requestHandler.respondToRequest();
        this.persistentState = (HashMap) response.get("state");
        byte[] outputMessage = (byte[]) response.get("message");
        OutputStream out = clientSocket.getOutputStream();
        out.write(outputMessage);
        out.close();
    }
}