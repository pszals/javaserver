import clojure.lang.PersistentVector;
import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;

import java.io.IOException;
import java.util.ArrayList;

public class ClojureInvoker {
    public static void loadClojure() {
        try {
            RT.load("clojure.core");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setUpTTTBoard() {
        Var require = RT.var("clojure.core", "require");
        require.invoke(Symbol.create("clojure-ttt.board"));
    }

    public static void setUpClojureAi() {
        Var require = RT.var("clojure.core", "require");
        require.invoke(Symbol.create("clojure-ttt.ai"));
    }

    public static void setUpClojureBoard() {
        loadClojure();
        setUpTTTBoard();
    }

    public Object gameOver(ArrayList board) throws IOException {
        setUpClojureBoard();
        Var gameOverStatus = RT.var("clojure-ttt.board", "game-over?");
        Object status = gameOverStatus.invoke(board);
        return status;
    }

    public Object winnerOnBoard(ArrayList board) {
        setUpClojureBoard();
        Var winnerStatus = RT.var("clojure-ttt.board", "winner?");
        Object status = winnerStatus.invoke(board);
        return status;
    }

    public Object getWinningPiece(ArrayList board) {
        setUpClojureBoard();
        Var getWinningPiece = RT.var("clojure-ttt.board", "winning-piece");
        Object winningPiece = getWinningPiece.invoke(board);
        return winningPiece;

    }

    public Object getBoardWithAiMove(ArrayList board) {
        loadClojure();
        setUpClojureAi();
        Var boardWithAiMove = RT.var("clojure-ttt.ai", "board-with-ai-move");
        Object aiMove = boardWithAiMove.invoke(PersistentVector.create(board));
        return aiMove;
    }

    public Object placePiece(int i, ArrayList board) {
        loadClojure();
        setUpClojureBoard();
        Var setPiece = RT.var("clojure-ttt.board", "place-piece");
        Object boardWithPlacedPiece = setPiece.invoke(i, "x", PersistentVector.create(board));
        return boardWithPlacedPiece;
    }
}
