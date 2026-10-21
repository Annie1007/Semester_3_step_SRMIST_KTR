import java.util.Scanner;

public class WordReverserPalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String word = scanner.next();
            StringBuilder sb = new StringBuilder(word);
            String reversed = sb.reverse().toString();

            if (word.equalsIgnoreCase(reversed)) {
                System.out.println(word + " - palindrome");
            } else {
                System.out.println(reversed + " - not a palindrome");
            }
        }
        scanner.close();
    }
}