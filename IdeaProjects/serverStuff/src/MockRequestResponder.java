public class MockRequestResponder implements IRequestResponder {
    public int timesCalled = 0;

    public void run() {
        timesCalled ++;
    }
}
