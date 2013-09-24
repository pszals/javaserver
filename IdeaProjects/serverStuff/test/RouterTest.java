import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RouterTest {
    @Test
    public void testRespondToMethodAndRoute() {

    Router router = new Router();
        String method = "GET";
        String route = "/";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route));

        method = "GET";
        route = "/foobar";
        assertEquals("HTTP/1.1 404 Not Found\r\n", router.respondToRouteRequest(method, route));

        method = "GET";
        route = "/redirect";
        assertEquals("HTTP/1.1 301 Permanently Moved\r\nLocation: http://localhost:5000/", router.respondToRouteRequest(method, route));

        method = "POST";
        route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route));

        method = "PUT";
        route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route));

        method = "options";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT", router.respondToRouteRequest(method, route));

        method = "GET";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route));

        method = "PUT";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route));

        method = "POST";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route));

        method = "HEAD";
        route = "/method_options";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route));
    }
}
