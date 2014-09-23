import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;


public class ClojureTTTBoardHolderTest {
    private ClojureTTTBoardHolder boardHolder = new ClojureTTTBoardHolder();

    @Test
    public void testWritesBoardStateToFile() throws IOException {
        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        ArrayList board = blankBoard();
        boardHolder.recordBoardState(board);

        assertEquals(new String(myFileReader.readFileContents(), "UTF-8"), "123456789");
    }

    @Test
    public void testReadFileWithBoardState() throws IOException {
        String boardState = boardHolder.readBoardStateFile();
        assert("123456789".equals(boardState));
    }

    private ArrayList blankBoard() {
        ArrayList board = new ArrayList();
        for (int x = 1; x < 10; x++) {
            board.add(x);
        }
        return board;
    }

    @Test
    public void testResetsGameState() throws IOException {
        ClojureTTTBoardHolder holder = new ClojureTTTBoardHolder();
        String blankBoard = "123456789";
        String fullBoard = "xoxoxooox";
        MyFileWriter myFileWriter = new MyFileWriter(System.getProperty("user.dir") + "/serverData/clojureTTTboard.txt");
        myFileWriter.write(fullBoard);
        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        holder.resetBoard();
        String resettedBoard = new String(myFileReader.readFileContents());

        assert(resettedBoard.equals(blankBoard));
    }
}
