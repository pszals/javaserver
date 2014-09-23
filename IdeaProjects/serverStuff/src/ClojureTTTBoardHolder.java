import java.io.IOException;
import java.util.ArrayList;

public class ClojureTTTBoardHolder {
    public void recordBoardState(ArrayList board) throws IOException {
        MyFileWriter myFileWriter = new MyFileWriter(System.getProperty("user.dir") + "/serverData/clojureTTTboard.txt");
        ClojureTTTBoardParser boardParser = new ClojureTTTBoardParser();
        String stringBoard = boardParser.convertBoardToString(board);

        myFileWriter.write(stringBoard);
    }

    public String readBoardStateFile() throws IOException {
        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        String boardString = new String(myFileReader.readFileContents(), "UTF-8");
        return boardString;
    }

    public void resetBoard() throws IOException {
        String blankBoard = "123456789";
        MyFileWriter myFileWriter = new MyFileWriter(System.getProperty("user.dir") + "/serverData/clojureTTTboard.txt");
        myFileWriter.write(blankBoard);
    }
}
