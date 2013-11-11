import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MockSocketTest {
    @Test
    public void testAccept() {
        MockSocket mockSocket = new MockSocket();
        MockSocket clientSocket = mockSocket.accept();
        assertThat(clientSocket, instanceOf(MockSocket.class));
    }

    @Test
    public void testGetOutputStream() {
        MockSocket mockSocket = new MockSocket();
        OutputStream outputStream = mockSocket.getOutputStream();
        assertThat(outputStream, instanceOf(OutputStream.class));
    }

    @Test
    public void testGetInputStream() {
        MockSocket mockSocket = new MockSocket();
        InputStream inputStream = mockSocket.getInputStream();
        assertThat(inputStream, instanceOf(InputStream.class));
    }

    @Test
    public void testOutput() {
        MockSocket mockSocket = new MockSocket();
        byte[] message = "message".getBytes();
        mockSocket.write(message);
    }

    @Test
    public void testWrite() {
        MockSocket mockSocket = new MockSocket();
        mockSocket.close();
        
        assertEquals(1, mockSocket.numberOfCallsToClose);
    }
}
