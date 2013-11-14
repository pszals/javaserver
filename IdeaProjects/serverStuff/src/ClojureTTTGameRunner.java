import java.io.IOException;
import java.util.ArrayList;

public class ClojureTTTGameRunner {

    public void placePiece(String move) throws IOException {
        ClojureBoardHolder boardHolder = new ClojureBoardHolder();
        ClojureBoardParser boardParser = new ClojureBoardParser();
        ClojureInvoker clojureTTT = new ClojureInvoker();
        String currentBoard = boardHolder.readBoardStateFile();
        ArrayList arrayBoard = boardParser.convertStringToBoardArray(currentBoard);

        if ((!move.equals("x") || !move.equals("o")) && clojureTTT.gameOver(arrayBoard).equals(false)) {
            int intMove = Integer.parseInt(move);
            Object newArrayBoard = clojureTTT.placePiece(intMove, arrayBoard);
            String stringBoard = boardParser.formatPVBoard(newArrayBoard.toString());

            arrayBoard = boardParser.convertStringToBoardArray(stringBoard);
            boardHolder.recordBoardState(arrayBoard);

            if (clojureTTT.gameOver(arrayBoard).equals(false)) {
                Object aiBoard = clojureTTT.getBoardWithAiMove(arrayBoard);
                stringBoard = boardParser.formatPVBoard(aiBoard.toString());
                arrayBoard = boardParser.convertStringToBoardArray(stringBoard);
                boardHolder.recordBoardState(arrayBoard);
            }
        }
    }
}
