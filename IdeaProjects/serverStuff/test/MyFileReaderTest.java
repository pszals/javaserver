import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;

public class MyFileReaderTest {
    @Test
    public void testReadsTextFromFile() throws IOException {

        File testFile = new File("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public/file1.txt");
        RandomAccessFile f = new RandomAccessFile(testFile, "r");
        byte[] b = new byte[(int)f.length()];

        f.read(b);

        String newB = new String(b);

        String fileName = "file1.txt";
        MyFileReader myFileReader = new MyFileReader(fileName);
        String fileContents = myFileReader.readTextFileContents();

        assertEquals("test\n", fileContents);
    }

    @Test
    public void testConvertsGiveFileNameToWholePath() {
        String myFile = "file1.txt";
        MyFileReader myFileReader = new MyFileReader(myFile);
        String path = "/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public/file1.txt";
        assertEquals(path, myFileReader.getPath());
    }
}
