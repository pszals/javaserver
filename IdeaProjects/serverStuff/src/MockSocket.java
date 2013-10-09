import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MockSocket extends Socket {
    public int numberOfCallsToClose;

    public MockSocket accept() {
        return new MockSocket();
    }

    @Override
    public OutputStream getOutputStream() {
        return new OutputStream() {
            @Override
            public void write(int i) throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }

    @Override
    public InputStream getInputStream() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }

    public void write(byte[] message) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void close() {
        numberOfCallsToClose += 1;
    }
}
