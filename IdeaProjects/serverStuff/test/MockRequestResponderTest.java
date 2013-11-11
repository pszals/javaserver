import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MockRequestResponderTest {
    @Test
    public void testCanBeRun() {
        MockRequestResponder mockRequestResponder = new MockRequestResponder();
        mockRequestResponder.run();
        assertEquals(1, mockRequestResponder.timesCalled);
    }
}
