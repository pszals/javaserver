import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class RequestResponder implements IRequestResponder {
    private final Socket connectionSocket;

    public RequestResponder(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            respondToRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void respondToRequest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        RequestHandler requestHandler = new RequestHandler(reader, new HashMap());
        HashMap response = requestHandler.respondToRequest();
        byte[] outputMessage = (byte[]) response.get("message");
        OutputStream out = connectionSocket.getOutputStream();

        out.write(outputMessage);
        out.close();
    }
}
