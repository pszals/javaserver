import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class RequestParserTest {
    @Test
    public void testReadMessageHead() {
        RequestParser requestParser;
        requestParser = new RequestParser();
        String message = "GET / HTTP/1.1";
        requestParser.readHead(message);

        assertEquals("GET", requestParser.getHttpMethod());

        requestParser = new RequestParser();
        message = "POST / HTTP/1.1";
        requestParser.readHead(message);

        assertEquals("POST", requestParser.getHttpMethod());

    }

    @Test
    public void testSplitRequestByLine() {
        RequestParser requestParser;
        requestParser = new RequestParser();
        String messageHead =
                "GET / HTTP/1.1\n" +
                "Host: localhost:5000\n" +
                "Connection: keep-alive\n";
        List<String> parsedMessageHead = requestParser.splitRequestByLine(messageHead);

        assertEquals("GET / HTTP/1.1", parsedMessageHead.get(0));
        assertEquals("Host: localhost:5000", parsedMessageHead.get(1));
        assertEquals("Connection: keep-alive", parsedMessageHead.get(2));
    }

    @Test
    public void testMapMultipleFields() {
        RequestParser requestParser;
        requestParser = new RequestParser();
        String messageHead =
                "Host: localhost:5000\n" +
                "Connection: keep-alive\n";
        ArrayList<String> splitRequest = requestParser.splitRequestByLine(messageHead);
        Map testMap = new HashMap();
        testMap.put("Host", "localhost:5000");
        testMap.put("Connection", "keep-alive");
        requestParser.mapFields(splitRequest);

        assertEquals(testMap, requestParser.getHeaderField());


    }

    @Test
    public void testParseRequest() {
        RequestParser requestParser;
        requestParser = new RequestParser();
        String request =    "GET / HTTP/1.1\n" +
                            "Host: localhost:5000\n" +
                            "Connection: keep-alive\n" +
                            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.65 Safari/537.36\n" +
                            "Accept-Encoding: gzip,deflate,sdch\n" +
                            "Accept-Language: en-US,en;q=0.8\n" +
                            "Cookie: textwrapon=false; wysiwyg=textarea";
        requestParser.parseRequest(request);
        assertEquals("GET", requestParser.getHttpMethod());
        assertEquals("/", requestParser.getRoute());
        assertEquals("HTTP/1.1", requestParser.getProtocol());
        assertEquals("localhost:5000", requestParser.getHeaderField().get("Host"));
    }

}
