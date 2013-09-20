import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        String line = null;
        try {
            clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(!(line = reader.readLine()).equals("")) {
                System.out.println(line);
            }
            OutputStream out = clientSocket.getOutputStream();
            out.write("HTTP/1.1 200 OK\r\n\r\nhi".getBytes(Charset.forName("utf-8")));
            out.close();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        clientSocket.close();
        serverSocket.close();
    }
}
