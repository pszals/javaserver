import java.io.*;

public class MyFileWriter {
    private final String pathWithFileName;

    public MyFileWriter(String pathWithFileName) {
        this.pathWithFileName = pathWithFileName;
    }

    public void write(String dataToBeWritten) throws IOException {
        FileOutputStream out = new FileOutputStream(pathWithFileName);
        out.write(dataToBeWritten.getBytes());
        out.close();
    }
}
