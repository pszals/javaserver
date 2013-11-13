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
}
