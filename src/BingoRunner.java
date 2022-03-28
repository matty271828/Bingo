public class BingoRunner {
  public static void main(String[] args) {
    // Create and execute instance of BingoController
    BingoController controller = new BingoController();

    // Invoke run()
    controller.run();

    // Print goodbye message
    System.out.printf("%s%n",Toolkit.GOODBYEMESSAGE);
  }
}
