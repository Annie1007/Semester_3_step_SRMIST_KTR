public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int totalChars = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char origMismatch = ' ', typedMismatch = ' ';

        for (int i = 0; i < totalChars; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based position indexing
                origMismatch = original.charAt(i);
                typedMismatch = typed.charAt(i);
            }
        }

        double accuracy = ((double) matched / totalChars) * 100;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matched, totalChars, accuracy);
        if (firstMismatchPos != -1) {
            System.out.printf("First Mismatch at position %d ('%c' vs '%c')%n", 
                    firstMismatchPos, origMismatch, typedMismatch);
        } else {
            System.out.println("No Mismatches");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}