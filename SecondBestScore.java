public class SecondBestScore {

    /**
     * Finds the second-highest distinct score in the array.
     * 
     * @param scores array of player scores
     * @return the second-highest distinct score, or -1 if it does not exist
     */
    public static int secondHighest(int[] scores) {
        if (scores == null || scores.length < 2) {
            return -1;
        }

        int highest = -1;
        int second = -1;

        for (int score : scores) {
            if (score > highest) {
                second = highest;
                highest = score;
            } else if (score > second && score < highest) {
                second = score;
            }
        }

        // If 'second' was never updated, it means all scores were identical
        return second;
    }

    public static void main(String[] args) {
        int[] sample1 = {45, 78, 92, 78, 60};
        int[] sample2 = {50, 50, 50};

        System.out.println("Sample 1 Output: " + secondHighest(sample1)); // Expected: 78
        System.out.println("Sample 2 Output: " + secondHighest(sample2)); // Expected: -1

        /*
         * COMPLEXITY REQUIREMENTS & COMPARISON WITH SORTING:
         * 
         * 1. Single-Pass Approach (Used Above):
         *    - Time Complexity: O(n), where n is the length of the array. We traverse 
         *      the array exactly once.
         *    - Space Complexity: O(1), as we only use two tracking variables 
         *      (highest and second).
         * 
         * 2. Comparison with Sorting First:
         *    - If we were to sort the array first (e.g., using Arrays.sort()), the 
         *      time complexity would increase to O(n log n), and space complexity could 
         *      be O(n) or O(log n) depending on the sorting algorithm.
         *    - Conclusion: The single-pass approach is significantly more efficient 
         *      in both time and space, and it successfully respects the constraint 
         *      to avoid sorting the array.
         */
    }
}