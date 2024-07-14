import java.util.Scanner;

public class Palindrome{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a new string");
        String input = scanner.nextLine();

        boolean Palindrome = true;
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                Palindrome = false;
                break;
            }
        }
        if (Palindrome) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }
    }
}
