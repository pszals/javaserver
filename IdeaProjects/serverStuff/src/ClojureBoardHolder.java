import java.io.IOException;
import java.util.ArrayList;

public class ClojureBoardHolder {
    private ArrayList board = new ArrayList();

    public void resetBoard() {
        ArrayList board = getBoard();
        for (int x = 1; x < 10; x++) {
            board.add(x);
        }
        setBoard(board);
    }

    public void placePiece(int square) {
        ArrayList board = getBoard();
        board.set((square - 1), "x");
        setBoard(board);
    }

    public ArrayList getBoard() {
        return board;
    }

    public void setBoard(ArrayList board) {
        this.board = board;
    }

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
}