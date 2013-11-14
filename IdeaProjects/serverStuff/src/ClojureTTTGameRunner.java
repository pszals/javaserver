import java.io.IOException;
import java.util.ArrayList;

public class ClojureTTTGameRunner {

    public void runCycle(String move) throws IOException {
        ClojureBoardHolder boardHolder = new ClojureBoardHolder();
        ClojureBoardParser boardParser = new ClojureBoardParser();
        ClojureInvoker clojureTTT      = new ClojureInvoker();

        String currentBoard = boardHolder.readBoardStateFile();
        ArrayList arrayBoard = boardParser.convertStringToBoardArray(currentBoard);

        if (validMove(move, clojureTTT, arrayBoard)) {
            arrayBoard = makeArrayBoardWithHumanMove(move, boardParser, clojureTTT, arrayBoard);
            boardHolder.recordBoardState(arrayBoard);

            if (gameIsNotOver(clojureTTT, arrayBoard)) {
                makeAiMove(boardHolder, boardParser, clojureTTT, arrayBoard);
            }
        }
    }

    private boolean gameIsNotOver(ClojureInvoker clojureTTT, ArrayList arrayBoard) throws IOException {
        return clojureTTT.gameOver(arrayBoard).equals(false);
    }

    private ArrayList makeArrayBoardWithHumanMove(String move, ClojureBoardParser boardParser, ClojureInvoker clojureTTT, ArrayList arrayBoard) {
        int intMove = Integer.parseInt(move);
        Object newArrayBoard = clojureTTT.placePiece(intMove, arrayBoard);
        String stringBoard = boardParser.formatPVBoard(newArrayBoard.toString());

        arrayBoard = boardParser.convertStringToBoardArray(stringBoard);
        return arrayBoard;
    }

    private boolean validMove(String move, ClojureInvoker clojureTTT, ArrayList arrayBoard) throws IOException {
        return (!move.equals("x") || !move.equals("o")) && gameIsNotOver(clojureTTT, arrayBoard);
    }

    private void makeAiMove(ClojureBoardHolder boardHolder, ClojureBoardParser boardParser, ClojureInvoker clojureTTT, ArrayList arrayBoard) throws IOException {
        String stringBoard;
        Object aiBoard = clojureTTT.getBoardWithAiMove(arrayBoard);
        stringBoard = boardParser.formatPVBoard(aiBoard.toString());
        arrayBoard = boardParser.convertStringToBoardArray(stringBoard);
        boardHolder.recordBoardState(arrayBoard);
    }
}
