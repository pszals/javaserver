import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class RouterTest {
    @Test
    public void testRespondToMethodAndRoute() throws IOException {

    Router router = new Router();
        byte[] body = "".getBytes();
        String method = "GET";
        String route = "/";
        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n<a href='file1'>file1</a><a href='file2'>file2</a><a href='image.gif'>image.gif</a><a href='image.jpeg'>image.jpeg</a><a href='image.png'>image.png</a><a href='text-file.txt'>text-file.txt</a><a href='partial_content.txt'>partial_content.txt</a>".
                getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "GET";
        route = "/foobar";
        assertArrayEquals("HTTP/1.1 404 Not Found\r\n".getBytes(), router.respondToRouteRequest(method, route, body));

        method = "GET";
        route = "/form";
        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "GET";
        route = "/redirect";
        assertArrayEquals("HTTP/1.1 301 Permanently Moved\r\nLocation: http://localhost:5000/".getBytes(), router.respondToRouteRequest(method, route, body));

        method = "POST";
        route = "/form";
        body = "'MY'='DATA'".getBytes();
        assertArrayEquals("HTTP/1.1 200 OK\r\n".getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "PUT";
        route = "/form";
        assertArrayEquals("HTTP/1.1 200 OK\r\n".getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "options";
        route = "/method_options";
        assertArrayEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "GET";
        route = "/method_options";
        assertArrayEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "PUT";
        route = "/method_options";
        assertArrayEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "POST";
        route = "/method_options";
        assertArrayEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes(), router.respondToRouteRequest(method, route, body));

        body = "".getBytes();
        method = "HEAD";
        route = "/method_options";
        assertArrayEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes(), router.respondToRouteRequest(method, route, body));

        method = "POST";
        route = "/form";
        body = "data=cosby".getBytes();
        assertArrayEquals("HTTP/1.1 200 OK\r\n".getBytes(), router.respondToRouteRequest(method, route, body));

        method = "PUT";
        route = "/file1";
        body = "".getBytes();
        assertArrayEquals("HTTP/1.1 405 Method Not Allowed\r\n".getBytes(), router.respondToRouteRequest(method, route, body));

        method = "POST";
        route = "/text-file.txt";
        body = "".getBytes();
        assertArrayEquals("HTTP/1.1 405 Method Not Allowed\r\n".getBytes(), router.respondToRouteRequest(method, route, body));

        method = "GET";
        route = "/partial_content.txt";
        body = "".getBytes();
        assertArrayEquals("HTTP/1.1 206 Partial Content\r\n\r\nThis".getBytes(), router.respondToRouteRequest(method, route, body));

    }

    @Test
    public void testInterpolatingNull() throws IOException {
        Router router = new Router();
        byte[] body = "".getBytes();
        String method = "GET";
        String route = "/form";
        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), router.respondToRouteRequest(method, route, body));    }
}
