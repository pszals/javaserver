import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MockServerSocketTest {
    @Test
    public void testReturnsInstanceOfClientSocket() throws IOException {
        MockServerSocket mockServerSocket = new MockServerSocket(4900);

        MockSocket mockSocket = (MockSocket) mockServerSocket.accept();

        assertThat(mockSocket, instanceOf(mockSocket.getClass()));
        assertEquals(1, mockServerSocket.numberOfCalls);

    }
}
