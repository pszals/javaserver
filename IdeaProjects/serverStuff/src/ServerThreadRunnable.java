public class ServerThreadRunnable implements Runnable{
    private IRequestResponder responder;

    public ServerThreadRunnable(IRequestResponder iRequestResponder){
        this.responder = iRequestResponder;
    }

    public void run() {
        this.responder.run();
    }
}