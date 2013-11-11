import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ServerThreadRunnbleTest {
    @Test
    public void testThreadIsRun() {
        MockRequestResponder mock = new MockRequestResponder();
        IRequestResponder interfaceResponder = mock;
        ServerThreadRunnable serverThreadRunnable = new ServerThreadRunnable(interfaceResponder);

        serverThreadRunnable.run();

        assertEquals(1, mock.timesCalled);

    }
}
