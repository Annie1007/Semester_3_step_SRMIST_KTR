import java.util.Arrays;

public class DutyRosterRotation {

    /**
     * Rotates the hostel roster names to the right by k places.
     * 
     * @param names array of names representing the roster
     * @param k number of places to rotate right
     * @return a new array containing the rotated roster
     */
    public static String[] rotateRoster(String[] names, int k) {
        if (names == null || names.length == 0) {
            return names;
        }

        int n = names.length;
        // Use k % n to remove redundant full turns
        int effectiveK = k % n;

        String[] rotated = new String[n];
        for (int i = 0; i < n; i++) {
            int newIndex = (i + effectiveK) % n;
            rotated[newIndex] = names[i];
        }

        return rotated;
    }

    public static void main(String[] args) {
        String[] names1 = {"A", "B", "C", "D", "E"};
        int k1 = 2;
        String[] result1 = rotateRoster(names1, k1);

        String[] names2 = {"A", "B", "C", "D", "E"};
        int k2 = 7;
        String[] result2 = rotateRoster(names2, k2);

        System.out.println("Sample 1 Output: " + Arrays.toString(result1)); // Expected: [D, E, A, B, C]
        System.out.println("Sample 2 Output: " + Arrays.toString(result2)); // Expected: [D, E, A, B, C]

        /*
         * COMPLEXITY REQUIREMENTS & EXPLANATION:
         * 
         * 1. Why shifting one place at a time, k times, is slow:
         *    - If you perform a single-step shift k times, the time complexity becomes O(n * k). 
         *      Since k can be as large as 10^9, this would result in billions of operations, 
         *      causing a Time Limit Exceeded (TLE) error.
         * 
         * 2. Optimal Solution Complexity (Implemented Above):
         *    - Time Complexity: O(n), where n is the length of the names array. 
         *      By using `k % n`, we skip unnecessary full rotations and place each element 
         *      in its final target index in a single pass.
         *    - Space Complexity: O(n) to construct the new output array. (Alternatively, 
         *      in-place reversal algorithms can achieve O(1) space, but O(n) auxiliary space 
         *      is optimal and clean for n <= 100).
         */
    }
}