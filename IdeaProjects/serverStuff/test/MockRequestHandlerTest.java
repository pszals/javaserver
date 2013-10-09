import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class MockRequestHandlerTest {
    @Test
    public void testRespondToRequest() {
        MockRequestHandler mockRequestHandler = new MockRequestHandler();
        HashMap output = mockRequestHandler.respondToRequest();

        assertThat(output, instanceOf(HashMap.class));
    }
}
