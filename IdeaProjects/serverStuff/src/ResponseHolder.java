import java.io.IOException;
import java.util.ArrayList;

public class ResponseHolder {
    public ResponseHolder() {
    }

    byte[] notFoundResponse() {
        byte[] response;
        response = "HTTP/1.1 404 Not Found\r\n".getBytes();
        return response;
    }

    byte[] methodNotAllowedResponse() {
        byte[] response;
        response = "HTTP/1.1 405 Method Not Allowed\r\n".getBytes();
        return response;
    }

    byte[] partialContentResponse() {
        byte[] response;
        response = "HTTP/1.1 206 Partial Content\r\n\r\nThis".getBytes();
        return response;
    }

    byte[] redirectResponse() {
        byte[] response;
        response = "HTTP/1.1 301 Permanently Moved\r\nLocation: http://localhost:5000/".getBytes();
        return response;
    }

    byte[] readFileResponse(String route, ResponseBuilder responseBuilder) throws IOException {
        byte[] response;
        MyFileReader myFileReader = new MyFileReader("cob_spec/public" + route);
        byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
        byte[] fileContents = myFileReader.readFileContents();
        response = responseBuilder.addTwoByteArrays(message, fileContents);
        return response;
    }

    byte[] getFormResponse(String route, ResponseBuilder responseBuilder) throws IOException {
        byte[] body;
        byte[] response;
        byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
        MyFileReader myFileReader = new MyFileReader("serverData" + route);
        body = myFileReader.readFileContents();
        response = responseBuilder.addTwoByteArrays(message, body);
        return response;
    }

    byte[] requestLogsResponse(byte[] body, ResponseBuilder responseBuilder) throws IOException {
        byte[] response;
        byte[] message = "HTTP/1.1".getBytes();
        response = responseBuilder.addTwoByteArrays(message, body);
        return response;
    }

    byte[] parametersResponse(byte[] body, ResponseBuilder responseBuilder) throws IOException {
        byte[] response;
        byte[] message = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
        response = responseBuilder.addTwoByteArrays(message, body);
        return response;
    }

    byte[] modifiedFormResponse(byte[] body) throws IOException {
        byte[] response;
        MyFileWriter myFileWriter = new MyFileWriter("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/form");
        myFileWriter.write(new String(body));
        response = "HTTP/1.1 200 OK\r\n".getBytes();
        return response;
    }

    byte[] methodOptionsResponse() {
        byte[] response;
        response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT".getBytes();
        return response;
    }

    byte[] indexResponse() {
        byte[] response;
        response = "HTTP/1.1 200 OK\r\n\r\n<a href='file1'>file1</a><a href='file2'>file2</a><a href='image.gif'>image.gif</a><a href='image.jpeg'>image.jpeg</a><a href='image.png'>image.png</a><a href='text-file.txt'>text-file.txt</a><a href='partial_content.txt'>partial_content.txt</a>".getBytes();
        return response;
    }

    byte[] tttHome(ArrayList board) {
        byte[] response;
        ClojureTTTPresenter presenter = new ClojureTTTPresenter();
        response = presenter.displayHtmlBoard(board).getBytes();
        return response;
    }
}