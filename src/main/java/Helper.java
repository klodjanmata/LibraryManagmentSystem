
import java.util.Scanner;

public class Helper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public static boolean readBoolean(String prompt) {
        return Boolean.parseBoolean(readString(prompt + " (true/false): "));
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static int getIntFromUser(String chose) {
        {

        }

        return 0;
    }
    }
