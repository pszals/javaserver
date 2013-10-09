import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class MyFileWriterTest {
    @Test
    public void testWritesToFile() throws IOException {
        String pathWithFileName = "/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/testfile.txt";
        String testData = "test data";
        MyFileWriter myFileWriter = new MyFileWriter(pathWithFileName);
        myFileWriter.write(testData);

        MyFileReader myFileReader = new MyFileReader("serverData/testfile.txt");

        byte[] bytesRead = myFileReader.readFileContents();
        assertArrayEquals(testData.getBytes(), bytesRead);
    }
}
