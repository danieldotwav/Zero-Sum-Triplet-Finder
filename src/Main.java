import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Case 1: Empty Array
        // Expected: []
        printTriplets(threeSum(new int[]{}));

        // Case 2: Array with No Triplets Summing to Zero
        // Expected: []
        printTriplets(threeSum(new int[]{1, 2, 3, 4}));

        // Case 3: Array with Multiple Duplicate Values
        // Expected: [[-1, -1, 2]]
        printTriplets(threeSum(new int[]{-1, -1, -1, 2, 2}));

        // Case 4: Array Where All Elements Are the Same
        // Expected: [[0, 0, 0]]
        printTriplets(threeSum(new int[]{0, 0, 0, 0}));

        // Case 5: Array with All Negative Numbers
        // Expected: []
        printTriplets(threeSum(new int[]{-5, -4, -3, -2, -1}));

        // Case 6: Array with All Positive Numbers
        // Expected: []
        printTriplets(threeSum(new int[]{1, 2, 3, 4, 5}));

        // Case 7: Large Array with Random Values
        // Expected: [ [ -6, 1, 5 ] [ -4, -1, 5 ] [ -4, 1, 3 ] [ -3, -2, 5 ] [ -3, 0, 3 ] [ -3, 1, 2 ] [ -2, -1, 3 ] [ -2, 0, 2 ] [ -2, 1, 1 ] [ -1, 0, 1 ] ]
        printTriplets(threeSum(new int[]{-2, -1, 0, 1, 1, 2, 3, -3, -4, 5, -6}));

        // Case 8: Array with Zeroes and Positive Numbers
        // Expected: [[0, 0, 0]]
        printTriplets(threeSum(new int[]{0, 0, 0, 1, 2, 3}));

        // Case 9: Edge Case
        // Expected: []
        printTriplets(threeSum(new int[]{1, 2, -2, -1}));
    }

    static List<List<Integer>> threeSum(int[] nums) {
        // Store triplets in a container
        List<List<Integer>> listOfExistingTriplets = new ArrayList<>();
        // Keep temporary lists in a hash set for efficient searching
        HashSet<String> currentTriplet = new HashSet<>();

        // Sort the array in ascending order
        Arrays.sort(nums);
        int arrSize = nums.length, target = 0, x;

        for (int i = 0; i < arrSize; ++i) {
            x = nums[i];
            int rightPtr = arrSize - 1;

            // Skip duplicate 'x' values
            if (!(i > 0 && nums[i] == nums[i - 1])) {
                for (int leftPtr = i + 1; leftPtr < rightPtr; ) {
                    int sum = x + nums[leftPtr] + nums[rightPtr];

                    // Process the valid triplet
                    if (sum == 0) {
                        // Check for duplicates
                        String tempKey = x + ", " + nums[leftPtr] + ", " + nums[rightPtr];

                        if (!currentTriplet.contains(tempKey)) {
                            currentTriplet.add(tempKey);
                            listOfExistingTriplets.add(new ArrayList<>(Arrays.asList(x, nums[leftPtr], nums[rightPtr])));
                        }

                        // Skip duplicate elements to the right of leftPtr
                        while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr + 1]) {
                            leftPtr++;
                        }

                        // Move to the next unique element for leftPtr
                        leftPtr++;
                    } 
                    else if (sum < target) {
                        leftPtr++; // Move leftPtr to the right to increase sum
                    } 
                    else {
                        rightPtr--; // Move rightPtr to the left to decrease sum
                    }
                }
            }
        }
        return listOfExistingTriplets;
    }

    static void printTriplets(List<List<Integer>> tripletList) {
        if (!tripletList.isEmpty()) {
            StringBuilder outputString = new StringBuilder();
            outputString.append(" [ ");
    
            for (List<Integer> triplet : tripletList) {
                outputString.append("[ ");
                for (int i = 0; i < triplet.size(); ++i) {
                    outputString.append(triplet.get(i));
                    if (i < triplet.size() - 1) {
                        outputString.append(", ");
                    }
                }
                outputString.append(" ] ");
            }
            outputString.append("]");
            System.out.println(outputString);
        }
        else {
            System.out.println(" []");
        }
    }
}
