import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class SocketMakerTest {
    @Test
    public void testMakesNewSocket() throws IOException {
        SocketMaker socketMaker = new SocketMaker();
        ServerSocket socket = socketMaker.establishPort(5001);

        assertThat(socket, instanceOf(ServerSocket.class));

        socket.close();
    }

}
