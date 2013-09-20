import org.junit.Test;
import static junit.framework.Assert.assertEquals;


public class HelloWorldTest {
    @Test
    public void testMessage() {
        HelloWorld helloWorld = new HelloWorld();
       assertEquals("hello", helloWorld.sayHello());
    }
}
