import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponseBuilder {
    public byte[] addTwoByteArrays(byte[] first, byte[] second) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );

        outputStream.write(first);
        outputStream.write(second);

        return outputStream.toByteArray( );
    }
}
