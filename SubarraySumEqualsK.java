import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        
        // Map to store frequency of running prefix sums: Map<PrefixSum, Frequency>
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Base case: empty prefix sum has a sum of 0 occurring once
        prefixSumMap.put(0, 1);

        for (int num : nums) {
            currentSum += num;

            // If (currentSum - k) exists in map, it means we found subarray(s) summing to k
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }

            // Record/update the frequency of currentSum
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        System.out.println(subarraySum(nums1, 2)); // Output: 2

        int[] nums2 = {1, -1, 0};
        System.out.println(subarraySum(nums2, 0)); // Output: 3
    }
}