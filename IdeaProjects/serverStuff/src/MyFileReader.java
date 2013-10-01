import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MyFileReader {
    private final String fileName;
    private String path;

    public MyFileReader(String fileName) {
        this.fileName = fileName;
        this.path = "/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/cob_spec/public" + fileName;
    }

    public byte[] readTextFileContents() throws IOException {
        File thisFile = new File(path);
        RandomAccessFile file = new RandomAccessFile(thisFile, "r");
        byte[] fileToBytes = new byte[(int)file.length()];

        file.read(fileToBytes);

        return fileToBytes;
    }

    public String getPath() {
        return path;
    }
}
