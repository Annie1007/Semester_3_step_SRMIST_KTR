public class ClassAttendanceTracker {

    /**
     * Analyzes attendance days to find total present days and the longest streak of consecutive present days.
     * 
     * @param days array of integers (1 for present, 0 for absent)
     * @return an int array where index 0 is total present days and index 1 is the longest streak
     */
    public static int[] attendanceSummary(int[] days) {
        if (days == null || days.length == 0) {
            return new int[] { 0, 0 };
        }

        int totalPresent = 0;
        int currentStreak = 0;
        int longestStreak = 0;

        for (int day : days) {
            if (day == 1) {
                totalPresent++;
                currentStreak++;
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                }
            } else {
                currentStreak = 0; // Reset streak on absence (0)
            }
        }

        return new int[] { totalPresent, longestStreak };
    }

    public static void main(String[] args) {
        int[] sample1 = {1, 1, 0, 1, 1, 1, 0, 1};
        int[] sample2 = {0, 0, 0};

        int[] result1 = attendanceSummary(sample1);
        int[] result2 = attendanceSummary(sample2);

        System.out.println("Sample 1 Output: Present: " + result1[0] + ", Longest streak: " + result1[1]);
        System.out.println("Sample 2 Output: Present: " + result2[0] + ", Longest streak: " + result2[1]);

        /*
         * COMPLEXITY REQUIREMENTS:
         * - Can both answers be found in a single pass? Yes! We calculate both the total present 
         *   count and the longest consecutive streak simultaneously while iterating through the array once.
         * - Time Complexity: O(n), where n is the length of the days array, because we traverse 
         *   the array in a single loop.
         * - Space Complexity: O(1), as we only use a few primitive tracking variables 
         *   (totalPresent, currentStreak, longestStreak) regardless of the array length.
         */
    }
}