import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RouterTest {
    @Test
    public void testRespondToMethodAndRoute() {

    Router router = new Router();
        RequestParser requestParser = new RequestParser();
        String body = "";
        String method = "GET";
        String route = "/";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "GET";
        route = "/foobar";
        assertEquals("HTTP/1.1 404 Not Found\r\n", router.respondToRouteRequest(method, route, body, requestParser));

        requestParser.setBody("data=cosby");
        method = "GET";
        route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n\r\ndata=cosby", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "GET";
        route = "/redirect";
        assertEquals("HTTP/1.1 301 Permanently Moved\r\nLocation: http://localhost:5000/", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "POST";
        route = "/form";
        body = "'MY'='DATA'";
        assertEquals("HTTP/1.1 200 OK\r\n\r\n'MY'='DATA'", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "PUT";
        route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "options";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "GET";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "PUT";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "POST";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body, requestParser));

        body = "";
        method = "HEAD";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body, requestParser));

        method = "POST";
        route = "/form";
        body = "data=cosby";
        assertEquals("HTTP/1.1 200 OK\r\n\r\ndata=cosby", router.respondToRouteRequest(method, route, body, requestParser));
    }
}
