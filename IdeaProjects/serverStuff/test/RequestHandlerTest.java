import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class RequestHandlerTest {

    @Test
    public void testReadMessageHead() throws UnsupportedEncodingException {
        String request = "GET / HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);
        requestHandler.parseHead(request);

        assertEquals("GET", requestHandler.getHttpMethod());

        BufferedReader newBufferedReader = new BufferedReader(new StringReader(request));
        requestHandler = new RequestHandler(newBufferedReader, state);
        request = "POST / HTTP/1.1";
        requestHandler.parseHead(request);

        assertEquals("POST", requestHandler.getHttpMethod());
    }

    @Test
    public void testSplitRequestByLine() {
        String request =
                "GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Connection: keep-alive\n";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);
        List<String> parsedMessageHead = requestHandler.splitRequestByLine(request);

        assertEquals("GET / HTTP/1.1", parsedMessageHead.get(0));
        assertEquals("Host: localhost:5000", parsedMessageHead.get(1));
        assertEquals("Connection: keep-alive", parsedMessageHead.get(2));
    }

    @Test
    public void testMapMultipleFields() {
        String request =
                "Host: localhost:5000\n" +
                "Connection: keep-alive\n";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);
        ArrayList<String> splitRequest = requestHandler.splitRequestByLine(request);
        Map testMap = new HashMap();
        testMap.put("Host", "localhost:5000");
        testMap.put("Connection", "keep-alive");
        requestHandler.mapFields(splitRequest);

        assertEquals(testMap, requestHandler.getHeaderFields());


    }

    @Test
    public void testParseRequest() throws UnsupportedEncodingException {
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Connection: keep-alive\n" +
                            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36\n" +
                            "Accept-Encoding: gzip,deflate,sdch\n" +
                            "Accept-Language: en-US,en;q=0.8\n" +
                            "Cookie: textwrapon=false; wysiwyg=textarea";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);
        requestHandler.parseRequest(request);
        assertEquals("GET", requestHandler.getHttpMethod());
        assertEquals("/", requestHandler.getRoute());
        assertEquals("HTTP/1.1", requestHandler.getProtocol());
        assertEquals("localhost:5000", requestHandler.getHeaderFields().get("Host"));
    }

    @Test
    public void testSplitBodyFromHead() throws UnsupportedEncodingException {
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Accept-Language: en-US,en;q=0.8\r\n\r\n" +
                            "data=cosby";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);
        requestHandler.parseRequest(request);
        assertEquals("GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Accept-Language: en-US,en;q=0.8", requestHandler.getHead());
    }

    @Test
    public void testAddSpacesAroundEqualsSigns() {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(""));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);
        String body = "data=cosby";
        assertEquals("data = cosby", requestHandler.addSpacesAroundEqualsSigns(body));
    }

    @Test
    public void testGetsStringRequestFromBufferedReader() throws IOException {
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Accept-Language: en-US,en;q=0.8\r\n\r\n";

        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);
        requestHandler.readHead();

        assertEquals(("GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Accept-Language: en-US,en;q=0.8\n"), requestHandler.getHead());

    }

    @Test
    public void testHandleRequest() throws IOException {
        String request =     "GET / HTTP/1.1\n" +
                             "Host: localhost:5000\n" +
                             "Accept-Language: en-US,en;q=0.8\r\n\r\n";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        HashMap response = requestHandler.respondToRequest();
        byte[] byteResponse = (byte[]) response.get("message");

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n<a href='file1'>file1</a><a href='file2'>file2</a><a href='image.gif'>image.gif</a><a href='image.jpeg'>image.jpeg</a><a href='image.png'>image.png</a><a href='text-file.txt'>text-file.txt</a><a href='partial_content.txt'>partial_content.txt</a>".
                getBytes(), byteResponse);

    }

    @Test
    public void testReadBytesFromBufferedReader() throws IOException {
        String request = "This is 16 bytes";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        assertEquals("This is 16 bytes", requestHandler.readBody(16));
    }

    @Test
    public void testPutsResponseHeaderIntoHashMap() throws IOException {
        String message =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\r\n\r\n";


        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        HashMap response = requestHandler.respondToRequest();

        Object header = response.get("message");

        assertEquals("HTTP/1.1 200 OK\r\n\r\n<a href='file1'>file1</a><a href='file2'>file2</a><a href='image.gif'>image.gif</a><a href='image.jpeg'>image.jpeg</a><a href='image.png'>image.png</a><a href='text-file.txt'>text-file.txt</a><a href='partial_content.txt'>partial_content.txt</a>", (new String((byte[]) header)));

    }

    @Test
    public void testPutsBodyIntoHashMap() throws IOException {
        String message =    "POST /form HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Content-Length: 5\r\n\r\n" +
                            "hello";


        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        HashMap response = requestHandler.respondToRequest();

        HashMap stateObject = (HashMap) response.get("state");
        byte[] nestedBody = (byte[]) stateObject.get("state");

        assertArrayEquals("hello".getBytes(), nestedBody);


    }

    @Test
    public void testParsesQueryString() throws IOException {
        String request = "GET /parameters?variable_1=Operators%20%3C HTTP/1.1";
        HashMap state = null;

        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        requestHandler.parseRoute("/parameters?variable_1=Operators%20%3C");

        assertEquals("/parameters", requestHandler.getRoute());
        assertEquals("variable_1=Operators%20%3C", requestHandler.getQueryString());

        requestHandler.parseQueryString(requestHandler.getQueryString());

        assertEquals("variable_1 = Operators <", new String(requestHandler.getBody()));

    }

    @Test
    public void testRespondsCorrectlyToQueryString() throws IOException {
        String request = "GET /parameters?variable_1=Operators%20%3C HTTP/1.1\r\n\r\n";
        HashMap state = null;

        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        requestHandler.respondToRequest();

        assertEquals("variable_1 = Operators <", new String(requestHandler.getBody()));
    }

    @Test
    public void testHandlesInvalidAuthentication() throws IOException {
        String message =    "GET /logs HTTP/1.1\n" +
                            "Authorization: Basic YWRtaW46aHVudGVyMh==\r\n\r\n";  //bad_credentials (terminal h instead of g)


        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        HashMap response = requestHandler.respondToRequest();

        Object header = response.get("message");
        Object credentials = response.get("Authorization");

        assertEquals("HTTP/1.1 401\r\n\r\nAuthentication required", new String((byte[]) header));
        assertEquals("Basic YWRtaW46aHVudGVyMh==", credentials);       //bad_credentials (terminal h instead of g)

        HashMap returnedState = (HashMap) response.get("state");
        String  requests = (String) returnedState.get("requests");

        assert(requests != null);
        }

    @Test
    public void testHandlesValidAuthentication() throws IOException {
        String message =    "GET /logs HTTP/1.1\n" +
                            "Authorization: Basic YWRtaW46aHVudGVyMg==\r\n\r\n";


        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        HashMap state = new HashMap();
        RequestHandler requestHandler = new RequestHandler(bufferedReader, state);

        HashMap response = requestHandler.respondToRequest();

        byte[] header = (byte[]) response.get("message");
        byte[] responseFromValidAuthentication = "HTTP/1.1 200 OK\r\n\r\nGET /log HTTP/1.1\nPUT /these HTTP/1.1\nHEAD /requests HTTP/1.1".getBytes();

        assertEquals(new String(responseFromValidAuthentication), new String(header));

    }
}