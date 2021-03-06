import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MyFileReader {
    private final String fileName;
    private String path;

    public MyFileReader(String fileName) {
        this.fileName = fileName;
        this.path = System.getProperty("user.dir") + "/" + fileName;
    }

    public byte[] readFileContents() throws IOException {
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
