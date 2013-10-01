import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class ResponseBuilderTest {

    @Test
    public void testAddsTwoByteArrays() throws IOException {
        byte[] first = "text".getBytes();
        byte[] second = "here".getBytes();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(first);
        outputStream.write(second);

        byte[] firstAndSecond = outputStream.toByteArray( );
        ResponseBuilder responseBuilder = new ResponseBuilder();

        assertArrayEquals(firstAndSecond, responseBuilder.addTwoByteArrays(first, second));
        assertEquals("texthere", new String(responseBuilder.addTwoByteArrays(first, second)));
    }


}
