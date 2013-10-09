import java.io.IOException;
import java.net.ServerSocket;

public class SocketMaker {
    public ServerSocket establishPort(int portNumber) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5000.");
            System.exit(1);
        }
        return serverSocket;
    }
}
