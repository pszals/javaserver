import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RouterTest {
    @Test
    public void testRespondToMethodAndRoute() {

    Router router = new Router();
        String body = "";
        String method = "GET";
        String route = "/";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "GET";
        route = "/foobar";
        assertEquals("HTTP/1.1 404 Not Found\r\n", router.respondToRouteRequest(method, route, body));

        method = "GET";
        route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "GET";
        route = "/redirect";
        assertEquals("HTTP/1.1 301 Permanently Moved\r\nLocation: http://localhost:5000/", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "POST";
        route = "/form";
        body = "'MY'='DATA'";
        assertEquals("HTTP/1.1 200 OK\r\n\r\n'MY'='DATA'", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "PUT";
        route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "options";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "GET";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "PUT";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "POST";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body));

        body = "";
        method = "HEAD";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route, body));

        method = "POST";
        route = "/form";
        body = "data=cosby";
        assertEquals("HTTP/1.1 200 OK\r\n\r\ndata=cosby", router.respondToRouteRequest(method, route, body));

        method = "PUT";
        route = "/file1";
        body = "";
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", router.respondToRouteRequest(method, route, body));

        method = "POST";
        route = "/text-file.txt";
        body = "";
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", router.respondToRouteRequest(method, route, body));
    }

    @Test
    public void testInterpolatingNull() {
        Router router = new Router();
        String body = null;
        String method = "GET";
        String route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", router.respondToRouteRequest(method, route, body));    }
}
