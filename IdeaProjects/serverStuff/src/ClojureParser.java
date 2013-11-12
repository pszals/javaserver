import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;
import com.clojure-ttt-0.1.0-SNAPSHOT;
import java.util.ArrayList;

public class ClojureParser {
    public static final Var REQUIRE = RT.var("clojure.core", "require");

    public static Object require(String nameSpace) {
        return REQUIRE.invoke(Symbol.intern(nameSpace));
    }

    static {
        require("my.special.namespace");
    }

    public Object gameOver(ArrayList board) {
        Var gameOverMethod = RT.var("clojure-ttt.board", "game-over?");

        Object gameOverStatus = gameOverMethod.invoke(board);

        return gameOverStatus;
    }
}
