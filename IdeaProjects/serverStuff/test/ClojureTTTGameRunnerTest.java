import org.junit.Test;

import java.io.IOException;

public class ClojureTTTGameRunnerTest {
    @Test
    public void testRunsThroughOneCycle() throws IOException {
        ClojureTTTGameRunner runner = new ClojureTTTGameRunner();
        ClojureTTTBoardHolder holder = new ClojureTTTBoardHolder();
        holder.resetBoard();
        String move = "9";
        String boardAfterTwoMoves = "x234o6789";
        MyFileWriter myFileWriter = new MyFileWriter(System.getProperty("user.dir") + "/serverData/clojureTTTboard.txt");
        myFileWriter.write(boardAfterTwoMoves);

        runner.runCycle(move);

        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        String boardAfterFourMoves = new String(myFileReader.readFileContents(), "UTF-8");

        assert("xo34o678x".equals(boardAfterFourMoves));
    }

}
