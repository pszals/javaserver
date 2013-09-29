import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class RequestParserTest {
    @Test
    public void testReadMessageHead() {
        String request = "GET / HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        RequestParser requestParser;
        requestParser = new RequestParser(bufferedReader);
        requestParser.parseHead(request);

        assertEquals("GET", requestParser.getHttpMethod());

        BufferedReader newBufferedReader = new BufferedReader(new StringReader(request));
        requestParser = new RequestParser(newBufferedReader);
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
        RequestParser requestParser;
        requestParser = new RequestParser(bufferedReader);
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
        RequestParser requestParser;
        requestParser = new RequestParser(bufferedReader);
        ArrayList<String> splitRequest = requestParser.splitRequestByLine(request);
        Map testMap = new HashMap();
        testMap.put("Host", "localhost:5000");
        testMap.put("Connection", "keep-alive");
        requestParser.mapFields(splitRequest);

        assertEquals(testMap, requestParser.getHeaderFields());


    }

    @Test
    public void testParseRequest() {
        RequestParser requestParser;
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Connection: keep-alive\n" +
                            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36\n" +
                            "Accept-Encoding: gzip,deflate,sdch\n" +
                            "Accept-Language: en-US,en;q=0.8\n" +
                            "Cookie: textwrapon=false; wysiwyg=textarea";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        requestParser = new RequestParser(bufferedReader);
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
        RequestParser requestParser;
        requestParser = new RequestParser(bufferedReader);
        requestParser.parseRequest(request);
        assertEquals("GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Accept-Language: en-US,en;q=0.8", requestParser.getHead());
        assertEquals("data = cosby", requestParser.getBody());
    }

    @Test
    public void testAddSpacesAroundEqualsSigns() {
        RequestParser requestParser;
        BufferedReader bufferedReader = new BufferedReader(new StringReader(""));
        requestParser = new RequestParser(bufferedReader);
        String body = "data=cosby";
        assertEquals("data = cosby", requestParser.addSpacesAroundEqualsSigns(body));
    }

    @Test
    public void testGetsStringRequestFromBufferedReader() throws IOException {
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Accept-Language: en-US,en;q=0.8\r\n\r\n";

        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        RequestParser requestParser;
        requestParser = new RequestParser(bufferedReader);
        requestParser.readHead();

        assertEquals(("GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Accept-Language: en-US,en;q=0.8\n"), requestParser.getHead());

    }

    @Test
    public void testHandleRequest() throws IOException {
        String request =    "GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Accept-Language: en-US,en;q=0.8\r\n\r\n";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        RequestParser requestParser = new RequestParser(bufferedReader);

        assertEquals("HTTP/1.1 200 OK\r\n", requestParser.respondToRequest());

    }

    @Test
    public void testReadBytesFromBufferedReader() throws IOException {
        String request = "This is 16 bytes";
        BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
        RequestParser requestParser = new RequestParser(bufferedReader);

        assertEquals("This is 16 bytes", requestParser.readBody(16));
    }

    @Test
    public void testHandleHeadWithBody() throws IOException {
        String message =    "POST /form HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Content-Length: 5\r\n\r\n" +
                            "hello"     +

                            "GET /form HTTP/1.1\n" +
                            "Host: localhost:5000\r\n\r\n" +

                            "POST /form HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Content-Length: 15\r\n\r\n" +
                            "data=heathcliff" +

                            "GET /form HTTP/1.1\n" +
                            "Host: localhost:5000\r\n\r\n";

        BufferedReader bufferedReader = new BufferedReader(new StringReader(message));
        RequestParser requestParser = new RequestParser(bufferedReader);

        requestParser.respondToRequest();
        requestParser.respondToRequest();
        requestParser.respondToRequest();
        requestParser.respondToRequest();


        assertEquals("data = heathcliff", requestParser.getBody());
    }
}
