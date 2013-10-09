import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ServerSocket injectedServerSocket;

    public Server (ServerSocket serverSocket) {
        this.injectedServerSocket = serverSocket;
    }

    public void start() throws IOException {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService serverRequestThreadPool = Executors.newFixedThreadPool(cores);
        ServerSocket serverSocket = injectedServerSocket;

        while (true) {
            RequestResponder requestResponder = new RequestResponder(serverSocket.accept());
            ServerThreadRunnable serverThreadRunnable = new ServerThreadRunnable(requestResponder);
            serverRequestThreadPool.submit(serverThreadRunnable);
        }
    }
}