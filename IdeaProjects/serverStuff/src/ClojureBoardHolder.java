import java.io.IOException;
import java.util.ArrayList;

public class ClojureBoardHolder {
    public void recordBoardState(ArrayList board) throws IOException {
        MyFileWriter myFileWriter = new MyFileWriter("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/clojureTTTboard.txt");
        ClojureBoardParser boardParser = new ClojureBoardParser();
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
        MyFileWriter myFileWriter = new MyFileWriter("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/clojureTTTboard.txt");
        myFileWriter.write(blankBoard);
    }
}
