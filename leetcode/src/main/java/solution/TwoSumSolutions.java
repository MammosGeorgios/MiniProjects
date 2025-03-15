package solution;

import java.util.HashMap;

/**
 * Problem url: https://leetcode.com/problems/two-sum/
 *
 * Test input:
 *
 * [2,7,11,15]
 * 9
 *
 */
public class TwoSumSolutions {

    private TwoSumSolutions() {
    }

    /** Simple solution with two for loops - About 92ms */
    public static int[] loopSolution(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }

            }
        }

        return new int[2];
    }

    /** HashMap Solution - About 3ms */
    public static int[] hastSolution(int[] nums, int target) {
        HashMap<Integer, Integer> indexMap = new HashMap<Integer,Integer>(); // creating the HashMap as with <Integer,Integer> is slower than empty <>
        for (int i = 0; i < nums.length; i++) {
            Integer requiredNum = (Integer) (target - nums[i]);
            if (indexMap.containsKey(requiredNum)) {
                return new int[]{indexMap.get(requiredNum), i};
            }

            indexMap.put(nums[i], i);
        }
        return new int[2];
    }

    /** Optimised HashMap Solution - 1ms */
    public static int[] hashSolutionFastest(int[] nums, int target){
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(nums[0], 0); //Set up the first number since there is no point in checking with an empty hash
        for (int i = 1; i < nums.length; i++) {

            if (indexMap.containsKey(target - nums[i])) {
                return new int[]{indexMap.get(target - nums[i]), i};
            }

            indexMap.put(nums[i], i);
        }
        return new int[2];
    }
}
