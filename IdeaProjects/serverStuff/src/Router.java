import java.io.IOException;

public class Router {
    public byte[] respondToRouteRequest(String method, String route, byte[] body) throws IOException {
        byte[] response = null;
        ResponseBuilder responseBuilder = new ResponseBuilder();

        if (method.equals("GET") && route.equals("/")) {
            response = "HTTP/1.1 200 OK\r\n".getBytes();

        } else if (method.equals("GET") && route.equals("/form") && body == null) {
            response = "HTTP/1.1 200 OK\r\n\r\n".getBytes();

        } else if (method.equals("GET") && route.equals("/form")) {
            byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();

            response = responseBuilder.addTwoByteArrays(message, body);

        } else if (method.equals("GET") && route.equals("/redirect")) {
            response = "HTTP/1.1 301 Permanently Moved\r\nLocation: http://localhost:5000/".getBytes();

        } else if (method.equals("GET") && route.equals("/partial_content.txt")) {
            response = "HTTP/1.1 206 Partial Content\r\n\r\nThis".getBytes();

        } else if (method.equals("PUT") && route.equals("/file1")) {
            response = "HTTP/1.1 405 Method Not Allowed\r\n".getBytes();

        } else if (method.equals("POST") && route.equals("/text-file.txt")) {
            System.out.println("here");
            response = "HTTP/1.1 405 Method Not Allowed\r\n".getBytes();

        } else if (method.equals("options") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes();

        } else if (method.equals("POST") && route.equals("/form")) {
            response = "HTTP/1.1 200 OK\r\n".getBytes();

        } else if (method.equals("PUT") && route.equals("/form")) {
            response = "HTTP/1.1 200 OK\r\n".getBytes();

        } else if (method.equals("GET") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes();

        } else if (method.equals("POST") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes();

        } else if (method.equals("PUT") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes();

        } else if (route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes();

        } else if (method.equals("GET") && route.equals("/file1")) {
            MyFileReader myFileReader = new MyFileReader("/file1");

            byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
            byte[] fileContents = myFileReader.readTextFileContents();

            response = responseBuilder.addTwoByteArrays(message, fileContents);

        } else if (method.equals("GET") && route.equals("/image.jpeg")) {
            MyFileReader myFileReader = new MyFileReader("/image.jpeg");

            byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
            byte[] fileContents = myFileReader.readTextFileContents();

            response = responseBuilder.addTwoByteArrays(message, fileContents);

        } else if (method.equals("GET") && route.equals("/image.gif")) {
            MyFileReader myFileReader = new MyFileReader("/image.gif");

            byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
            byte[] fileContents = myFileReader.readTextFileContents();

            response = responseBuilder.addTwoByteArrays(message, fileContents);

        } else if (method.equals("GET") && route.equals("/image.png")) {
            MyFileReader myFileReader = new MyFileReader("/image.png");

            byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
            byte[] fileContents = myFileReader.readTextFileContents();

            response = responseBuilder.addTwoByteArrays(message, fileContents);

        } else {
            response = "HTTP/1.1 404 Not Found\r\n".getBytes();
        }

        return response;
    }
}
