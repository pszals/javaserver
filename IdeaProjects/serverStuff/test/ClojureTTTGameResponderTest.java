import org.junit.Test;

import java.io.IOException;

public class ClojureTTTGameResponderTest {
    @Test
    public void testPlacesMoveOnBoard() throws IOException {
        ClojureTTTGameResponder responder = new ClojureTTTGameResponder();
        String move = "3";
        String board = "123456789";
        MyFileWriter myFileWriter = new MyFileWriter("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/clojureTTTboard.txt");
        myFileWriter.write(board);

        responder.placePiece(move);

        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        String boardString = new String(myFileReader.readFileContents(), "UTF-8");

        assert("12x456789".equals(boardString));
    }

    @Test
    public void testResetsGameState() throws IOException {
        ClojureTTTGameResponder responder = new ClojureTTTGameResponder();
        String blankBoard = "123456789";
        String fullBoard = "xoxoxooox";
        MyFileWriter myFileWriter = new MyFileWriter("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/clojureTTTboard.txt");
        myFileWriter.write(fullBoard);
        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        responder.resetBoard();
        String resettedBoard = new String(myFileReader.readFileContents());

        assert(resettedBoard.equals(blankBoard));


    }
}
