import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class MyFileReaderTest {
    @Test
    public void testReadsTextFromFile() throws IOException {
        File testFile = new File(System.getProperty("user.dir") + "/cob_spec/public/file1");
        RandomAccessFile f = new RandomAccessFile(testFile, "r");
        byte[] b = new byte[(int)f.length()];

        String fileName = "cob_spec/public/file1";
        MyFileReader myFileReader = new MyFileReader(fileName);
        byte[] fileContents = myFileReader.readFileContents();

        assertEquals("file1 contents", new String(fileContents));
    }

    @Test
    public void testConvertsGivenFileNameToWholePath() {
        String myFile = "cob_spec/public/file1";
        MyFileReader myFileReader = new MyFileReader(myFile);
        String path = System.getProperty("user.dir") + "/cob_spec/public/file1";
        assertEquals(path, myFileReader.getPath());
    }

    @Test
    public void testReadsImageInBytesFromFile() throws IOException {
        File testFile = new File(System.getProperty("user.dir") + "/cob_spec/public/image.png");
        RandomAccessFile f = new RandomAccessFile(testFile, "r");
        byte[] b = new byte[(int)f.length()];

        f.read(b);

        String fileName = "cob_spec/public/image.png";
        MyFileReader myFileReader = new MyFileReader(fileName);
        byte[] fileContents = myFileReader.readFileContents();

        assertArrayEquals(b, fileContents);
    }

}
