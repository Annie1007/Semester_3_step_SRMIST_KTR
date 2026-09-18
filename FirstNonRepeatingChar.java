import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        // Use an array to count ASCII frequencies (O(N) runtime, O(1) space)
        int[] charCounts = new int[256];

        for (int i = 0; i < text.length(); i++) {
            charCounts[text.charAt(i)]++;
        }

        // Early-exit scan left to right
        for (int i = 0; i < text.length(); i++) {
            if (charCounts[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0'; // Return null character if no non-repeating character exists
    }

    public static void processInput(String input) {
        char result = findFirstNonRepeatingChar(input);
        
        if (result != '\0') {
            System.out.printf("Input: \"%s\" -> First Non-Repeating Character: '%c'%n", input, result);
        } else {
            System.out.printf("Input: \"%s\" -> No Non-Repeating Character Found%n", input);
        }
    }

    public static void main(String[] args) {
        processInput("swiss");
        processInput("aabbcc");
    }
}