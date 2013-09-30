import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RouterTest {
    @Test
    public void testRespondToMethodAndRoute() throws IOException {

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

        method = "POST";
        route = "/form";
        body = "'MY'='DATA'";
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route, body));

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
        assertEquals("HTTP/1.1 200 OK\r\n", router.respondToRouteRequest(method, route, body));

        method = "PUT";
        route = "/file1";
        body = "";
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", router.respondToRouteRequest(method, route, body));

        method = "POST";
        route = "/text-file.txt";
        body = "";
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", router.respondToRouteRequest(method, route, body));

        method = "GET";
        route = "/file1";
        assertEquals("HTTP/1.1 200 OK\r\n\r\nfile1 contents", router.respondToRouteRequest(method, route, body));

        File file1 = new File ("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public/file1.txt");
        // make universal path
        file1.createNewFile();
        Assert.assertEquals(true, file1.isFile());
        assertThat(file1, instanceOf(File.class));

        File workingDirectory = new File(System.getProperty("user.dir"));

//        File file2 = new File ("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public");
//        assertTrue(file2.isDirectory());
//        Assert.assertEquals("file1 contents", file1.canRead());
//        "file1 contents".getBytes()
                byte[] myBytes = "file1 contents".getBytes();
        String myBytesInString = new String(myBytes);
        Assert.assertEquals("file1 contents", myBytesInString);
    }

    @Test
    public void testInterpolatingNull() throws IOException {
        Router router = new Router();
        String body = null;
        String method = "GET";
        String route = "/form";
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", router.respondToRouteRequest(method, route, body));    }
}
