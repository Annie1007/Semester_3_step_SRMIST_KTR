public class ExamScoreBandCounter {

    /**
     * Counts how many scores fall within the inclusive range [low, high]
     * using binary search (lower bound and upper bound approach).
     * 
     * @param scores sorted array of student marks
     * @param low lower bound (inclusive)
     * @param high upper bound (inclusive)
     * @return the number of scores in the band
     */
    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        // 1. Find the first index where score >= low
        int leftIndex = findLowerBound(scores, low);

        // 2. Find the first index where score > high
        int rightIndex = findUpperBound(scores, high);

        // The count is the difference between these two indices
        return rightIndex - leftIndex;
    }

    // Helper: Finds the first index where element >= target
    private static int findLowerBound(int[] scores, int target) {
        int low = 0;
        int high = scores.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Helper: Finds the first index where element > target
    private static int findUpperBound(int[] scores, int target) {
        int low = 0;
        int high = scores.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (scores[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        int count1 = countInBand(scores, 42, 58);
        int count2 = countInBand(scores, 90, 100);

        System.out.println("Sample 1 Output: " + count1); // Expected: 6
        System.out.println("Sample 2 Output: " + count2); // Expected: 0

        /*
         * COMPLEXITY REQUIREMENTS & ANALYSIS:
         * 
1. Linear Scan Approach:
         *    - Time Complexity: O(n) per query, where n is the length of the array. 
         *      If queried often, this becomes extremely slow for large arrays (up to 10^6 elements).
         *    - Space Complexity: O(1).
         * 
         * 2. Binary Search Approach (Implemented Above):
         *    - Time Complexity: O(log n) per query, because we use modified binary search 
         *      twice to find boundaries. This is optimal for frequent queries.
         *    - Space Complexity: O(1), as it uses constant extra space.
         * 
         * 3. How Duplicates Affect the Search:
         *    - Because scores can duplicate (e.g., multiple 58s), a standard binary search 
         *      will stop at *any* matching element randomly. 
         *    - To accurately count items in the band, the binary search must be modified to find 
         *      the exact *boundaries*: the **first occurrence** of a number >= low, and the **first occurrence** 
         *      of a number strictly > high. Subtracting these two indices precisely accounts for 
         *      all duplicates at the edges.
         */
    }
}