public class RequestResponderTest {
//    @Test
//    public void testRespondsToRequest() throws IOException {
//        HashMap persistentState = new HashMap();
//        ServerSocket serverSocket = new ServerSocket(4999);
//        serverSocket.accept();
//        RequestResponder requestResponder = new RequestResponder(serverSocket, persistentState);
//
//        Socket clientSocket = new Socket("localhost", 4999);
//        PrintWriter  out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//        out.write("GET / HTTP/1.1\r\n\r\n");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//        requestResponder.respondToRequest();
//
//        String line;
//        String head = "";
//        try {
//            while(!(line = reader.readLine()).equals("")) {
//                head += line + "\n";
//            }
//        }
//        catch (IOException e) {
//            System.err.println("Accept failed.");
//            System.exit(1);
//        }
//        out.close();
//        serverSocket.close();
//        clientSocket.close();
//
//        //typecast to buffered reader testSocket.getInputStream();
//
//        assertEquals("HTTP/1.1 200 OK", head);
//
//    }
}
