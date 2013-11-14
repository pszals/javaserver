import java.io.IOException;
import java.util.ArrayList;

public class ClojureTTTGameResponder {

    public void placePiece(String move) throws IOException {
        ClojureBoardHolder boardHolder = new ClojureBoardHolder();
        ClojureBoardParser boardParser = new ClojureBoardParser();
        ClojureInvoker clojureTTT = new ClojureInvoker();
        String currentBoard = boardHolder.readBoardStateFile();
        ArrayList arrayBoard = boardParser.convertStringToBoardArray(currentBoard);

        if (!move.equals("x") || !move.equals("o")) {
            int intMove = Integer.parseInt(move);
            Object newArrayBoard = clojureTTT.placePiece(intMove, arrayBoard);
            String stringBoard = boardParser.formatPVBoard(newArrayBoard.toString());

            arrayBoard = boardParser.convertStringToBoardArray(stringBoard);
            boardHolder.recordBoardState(arrayBoard);
        }
    }

    public void resetBoard() throws IOException {
        String blankBoard = "123456789";
        MyFileWriter myFileWriter = new MyFileWriter("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/clojureTTTboard.txt");
        myFileWriter.write(blankBoard);
    }
}
