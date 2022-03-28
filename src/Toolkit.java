import java.util.Scanner;

public class Toolkit {
    private static final Scanner stdIn = new Scanner(System.in);

    /* GOODBYEMESSAGE */
    public static final String GOODBYEMESSAGE = "Thank you for playing";

    public static String getInputForMessage(String message) {
        System.out.println(message);
        return stdIn.nextLine();
    }

    public static String printArray(String[] array) {
        StringBuilder sb = new StringBuilder();
    /*
        create a loop to print the numbers out once a user has inputted the BingoCard numbers, separated by commas (trim leading / trailing spaces)
        check the expected output here to ensure that it appears as it should
        return as a sb.toString()
   */
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i != array.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
