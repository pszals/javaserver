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

    public static void requireClojureTTT() {
        Var require = RT.var("clojure.core", "require");
        require.invoke(Symbol.create("clojure-ttt.board"));
    }

    public static void setUpClojureEnv() {
        loadClojure();
        requireClojureTTT();
    }

    public Object gameOver(ArrayList board) throws IOException {
        setUpClojureEnv();
        Var gameOverStatus = RT.var("clojure-ttt.board", "game-over?");
        Object status = gameOverStatus.invoke(board);
        return status;
    }

    public Object winnerOnBoard(ArrayList board) {
        setUpClojureEnv();
        Var winnerStatus = RT.var("clojure-ttt.board", "winner?");
        Object status = winnerStatus.invoke(board);
        return status;
    }

    public Object getWinningPiece(ArrayList board) {
        setUpClojureEnv();
        Var getWinningPiece = RT.var("clojure-ttt.board", "winning-piece");
        Object winningPiece = getWinningPiece.invoke(board);
        return winningPiece;

    }

    public Object getAiMove(ArrayList board) {
        loadClojure();
        Var require = RT.var("clojure.core", "require");
        require.invoke(Symbol.create("clojure-ttt.ai"));
        Var boardWithAiMove = RT.var("clojure-ttt.ai", "board-with-ai-move");

        Object aiMove = boardWithAiMove.invoke(board);
        return aiMove;
    }
}
