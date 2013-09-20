public class Hello {

    private String message;

    public void output() {
        sayHello();
        System.out.println(message);
    }

    public void sayHello() {
      message = "Hello Junit";
    }

    public void anotherSayHello() {
      message = "goodbye!";
    }
}