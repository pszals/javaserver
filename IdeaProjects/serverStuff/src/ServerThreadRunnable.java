import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public class ServerThreadRunnable implements Runnable{
    private final ServerSocket serverSocket;
    HashMap persistentState;

    public ServerThreadRunnable(ServerSocket serverSocket, HashMap persistentState) throws IOException {
        this.serverSocket = serverSocket;
        this.persistentState = persistentState;
    }

    public void run() {
        ExecutorService serverRequestThreadPool = null;
        try {
            Socket clientSocket = this.serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            RequestHandler requestHandler = new RequestHandler(reader, persistentState);
            HashMap response = requestHandler.respondToRequest();
            this.persistentState = (HashMap) response.get("state");
            byte[] outputMessage = (byte[]) response.get("message");
            OutputStream out = clientSocket.getOutputStream();
            out.write(outputMessage);
            out.close();
        }
        catch (IOException e) {
            System.out.println("system error");
        }
    }
}