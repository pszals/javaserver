import java.io.IOException;
import java.util.ArrayList;

public class ClojureTTTGameRunner {

    public void runCycle(String move) throws IOException {
        ClojureTTTBoardHolder boardHolder = new ClojureTTTBoardHolder();
        ClojureTTTBoardParser boardParser = new ClojureTTTBoardParser();
        ClojureTTTInvoker clojureTTT      = new ClojureTTTInvoker();

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

    private boolean gameIsNotOver(ClojureTTTInvoker clojureTTT, ArrayList arrayBoard) throws IOException {
        return clojureTTT.gameOver(arrayBoard).equals(false);
    }

    private ArrayList makeArrayBoardWithHumanMove(String move, ClojureTTTBoardParser boardParser, ClojureTTTInvoker clojureTTT, ArrayList arrayBoard) {
        int intMove = Integer.parseInt(move);
        Object newArrayBoard = clojureTTT.placePiece(intMove, arrayBoard);
        String stringBoard = boardParser.formatClojureBoard(newArrayBoard.toString());

        arrayBoard = boardParser.convertStringToBoardArray(stringBoard);
        return arrayBoard;
    }

    private boolean validMove(String move, ClojureTTTInvoker clojureTTT, ArrayList arrayBoard) throws IOException {
        return (!move.equals("x") || !move.equals("o")) && gameIsNotOver(clojureTTT, arrayBoard);
    }

    private void makeAiMove(ClojureTTTBoardHolder boardHolder, ClojureTTTBoardParser boardParser, ClojureTTTInvoker clojureTTT, ArrayList arrayBoard) throws IOException {
        String stringBoard;
        Object aiBoard = clojureTTT.getBoardWithAiMove(arrayBoard);
        stringBoard = boardParser.formatClojureBoard(aiBoard.toString());
        arrayBoard = boardParser.convertStringToBoardArray(stringBoard);
        boardHolder.recordBoardState(arrayBoard);
    }
}
