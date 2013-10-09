import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocket extends ServerSocket {
    public int numberOfCalls;

    public MockServerSocket(int i) throws IOException {
        super(i);
    }

    public Socket accept() {
        MockSocket socket = new MockSocket();
        numberOfCalls += 1;
        return socket;
    }
}
