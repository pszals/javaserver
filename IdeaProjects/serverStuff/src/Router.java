public class Router {
    public String respondToRouteRequest(String method, String route) {
        String response = null;
        if (method.equals("GET") && route.equals("/")) {
            response = "HTTP/1.1 200 OK\r\n";
        } else if (method.equals("GET") && route.equals("/form")) {
            response = "HTTP/1.1 200 OK\r\n";
        } else if (method.equals("GET") && route.equals("/redirect")) {
            response = "HTTP/1.1 301 Permanently Moved\r\nLocation: http://localhost:5000/";
        } else if (method.equals("GET") && route.equals("/file1")) {
            response = "HTTP/1.1 405 Method not allowed\r\n";
        } else if (method.equals("GET") && route.equals("/text-file.txt")) {
            response = "HTTP/1.1 405 Method not allowed\r\n";
        } else if (method.equals("options") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT";
        } else if (method.equals("POST") && route.equals("/form")) {
            response = "HTTP/1.1 200 OK\r\n";
        } else if (method.equals("PUT") && route.equals("/form")) {
            response = "HTTP/1.1 200 OK\r\n";
        } else if (method.equals("GET") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT";
        } else if (method.equals("POST") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT";
        } else if (method.equals("PUT") && route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT";
        } else if (route.equals("/method_options")) {
            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT";
        }
        else {
            response = "HTTP/1.1 404 Not Found\r\n";
        }

        return response;
    }
}
