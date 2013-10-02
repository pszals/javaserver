import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class RequestParserTest {
    @Test
    public void testReadMessageHead() {
        String request = "GET / HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);
        requestParser.parseHead(request);

        assertEquals("GET", requestParser.getHttpMethod());

        BufferedReader newBufferedReader = new BufferedReader(new StringReader(request));
        requestParser = new RequestParser(newBufferedReader, state);
        request = "POST / HTTP/1.1";
        requestParser.parseHead(request);

        assertEquals("POST", requestParser.getHttpMethod());
    }

    @Test
    public void testSplitRequestByLine() {
        String request =
                "GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Connection: keep-alive\n";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);
        List<String> parsedMessageHead = requestParser.splitRequestByLine(request);

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
        RequestParser requestParser = new RequestParser(bufferedReader, state);
        ArrayList<String> splitRequest = requestParser.splitRequestByLine(request);
        Map testMap = new HashMap();
        testMap.put("Host", "localhost:5000");
        testMap.put("Connection", "keep-alive");
        requestParser.mapFields(splitRequest);

        assertEquals(testMap, requestParser.getHeaderFields());


    }

    @Test
    public void testParseRequest() {
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
        RequestParser requestParser = new RequestParser(bufferedReader, state);
        requestParser.parseRequest(request);
        assertEquals("GET", requestParser.getHttpMethod());
        assertEquals("/", requestParser.getRoute());
        assertEquals("HTTP/1.1", requestParser.getProtocol());
        assertEquals("localhost:5000", requestParser.getHeaderFields().get("Host"));
    }

    @Test
    public void testSplitBodyFromHead() {
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Accept-Language: en-US,en;q=0.8\r\n\r\n" +
                            "data=cosby";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);
        requestParser.parseRequest(request);
        assertEquals("GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Accept-Language: en-US,en;q=0.8", requestParser.getHead());
    }

    @Test
    public void testAddSpacesAroundEqualsSigns() {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(""));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);
        String body = "data=cosby";
        assertEquals("data = cosby", requestParser.addSpacesAroundEqualsSigns(body));
    }

    @Test
    public void testGetsStringRequestFromBufferedReader() throws IOException {
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Accept-Language: en-US,en;q=0.8\r\n\r\n";

        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);
        requestParser.readHead();

        assertEquals(("GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Accept-Language: en-US,en;q=0.8\n"), requestParser.getHead());

    }

    @Test
    public void testHandleRequest() throws IOException {
        String request =     "GET / HTTP/1.1\n" +
                             "Host: localhost:5000\n" +
                             "Accept-Language: en-US,en;q=0.8\r\n\r\n";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);

        HashMap response = requestParser.respondToRequest();
        byte[] byteResponse = (byte[]) response.get("message");

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n<a href='file1'>file1</a><a href='file2'>file2</a><a href='image.gif'>image.gif</a><a href='image.jpeg'>image.jpeg</a><a href='image.png'>image.png</a><a href='text-file.txt'>text-file.txt</a><a href='partial_content.txt'>partial_content.txt</a>".
                getBytes(), byteResponse);

    }

    @Test
    public void testReadBytesFromBufferedReader() throws IOException {
        String request = "This is 16 bytes";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);

        assertEquals("This is 16 bytes", requestParser.readBody(16));
    }

    @Test
    public void testPutsResponseHeaderIntoHashMap() throws IOException {
        String message =    "GET /form HTTP/1.1\n" +
                            "Host: localhost:5000\r\n\r\n";


        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);

        HashMap response = requestParser.respondToRequest();

        Object header = response.get("message");

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), (byte[]) header);

    }

    @Test
    public void testPutsBodyIntoHashMap() throws IOException {
        String message =    "POST /form HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Content-Length: 5\r\n\r\n" +
                            "hello";


        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        HashMap state = new HashMap();
        RequestParser requestParser = new RequestParser(bufferedReader, state);

        HashMap response = requestParser.respondToRequest();

        HashMap stateObject = (HashMap) response.get("state");
        byte[] nestedBody = (byte[]) stateObject.get("state");

        assertArrayEquals("hello".getBytes(), nestedBody);


    }

    @Test
    public void testPutsBodyFromStateIntoBodyVariable() throws IOException {
        String message =    "GET /form HTTP/1.1\n" +
                            "Host: localhost:5000\r\n\r\n";


        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        HashMap state = new HashMap();
        state.put("state", "hello".getBytes());

        RequestParser requestParser = new RequestParser(bufferedReader, state);

        requestParser.respondToRequest();

        assertArrayEquals("hello".getBytes(), requestParser.getBody());
    }

    @Test
    public void testParsesQueryString() throws IOException {
        String request = "GET /parameters?variable_1=Operators%20 HTTP/1.1";
        HashMap state = null;

        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        RequestParser requestParser = new RequestParser(bufferedReader, state);

        requestParser.parseRoute("/parameters?variable_1=Operators%20%3C");

        assertEquals("/parameters", requestParser.getRoute());
        assertEquals("variable_1=Operators%20%3C", requestParser.getQueryString());

        requestParser.parseQueryString(requestParser.getQueryString());

        assertEquals("variable_1 = Operators <", new String(requestParser.getBody()));

    }
}
