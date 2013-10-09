import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws IOException {
        SocketMaker socketMaker = new SocketMaker();
        ServerSocket serverSocket = socketMaker.establishPort(5000);
        Server server = new Server(serverSocket);
        server.start();
    }
}
