public class PalindromeChecker {

    // Approach 1: Iterative Comparison
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: Recursion
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // Approach 3: Array Reversal
    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return String.valueOf(original).equalsIgnoreCase(String.valueOf(reversed));
    }

    public static void verifyPalindrome(String text) {
        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayRev = isPalindromeArrayReversal(text);

        String format = "%s: %s | %s: %s | %s: %s%n";
        System.out.printf("Input: \"%s\"%n", text);
        System.out.printf(format, 
                "Iterative", (iterative ? "Palindrome" : "Not Palindrome"),
                "Recursive", (recursive ? "Palindrome" : "Not Palindrome"),
                "Array Reversal", (arrayRev ? "Palindrome" : "Not Palindrome"));
        System.out.println();
    }

    public static void main(String[] args) {
        verifyPalindrome("madam");
        verifyPalindrome("hello");
    }
}