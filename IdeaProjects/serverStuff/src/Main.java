import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}
