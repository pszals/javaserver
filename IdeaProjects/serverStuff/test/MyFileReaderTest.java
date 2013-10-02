import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class MyFileReaderTest {
    @Test
    public void testReadsTextFromFile() throws IOException {

        File testFile = new File("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public/file1");
        RandomAccessFile f = new RandomAccessFile(testFile, "r");
        byte[] b = new byte[(int)f.length()];

        String fileName = "/file1";
        MyFileReader myFileReader = new MyFileReader(fileName);
        byte[] fileContents = myFileReader.readFileContents();

        assertEquals("file1 contents\n", new String(fileContents));
    }

    @Test
    public void testConvertsGivenFileNameToWholePath() {
        String myFile = "/file1";
        MyFileReader myFileReader = new MyFileReader(myFile);
        String path = "/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public/file1";
        assertEquals(path, myFileReader.getPath());
    }

    @Test
    public void testReadsImageInBytesFromFile() throws IOException {

        File testFile = new File("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public/image.png");
        RandomAccessFile f = new RandomAccessFile(testFile, "r");
        byte[] b = new byte[(int)f.length()];

        f.read(b);

        String fileName = "/image.png";
        MyFileReader myFileReader = new MyFileReader(fileName);
        byte[] fileContents = myFileReader.readFileContents();

        assertArrayEquals(b, fileContents);
    }

}
