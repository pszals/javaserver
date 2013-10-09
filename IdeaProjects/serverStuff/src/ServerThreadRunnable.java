public class ServerThreadRunnable implements Runnable{
    private RequestResponder responder;

    public ServerThreadRunnable(RequestResponder responder){
        this.responder = responder;
    }

    public void run() {
        this.responder.run();
    }
}