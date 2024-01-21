# Zero Sum Triplet Finder

## Introduction

This Java project provides a solution to the classic "Three Sum Problem". The goal is to identify all unique triplets in an integer array that sum up to zero. This implementation not only finds these triplets but also ensures that no duplicate triplets are included in the output.

## Algorithm

### **1. threeSum()**

#### Logic

- Sort the input array.
- Use a combination of a fixed pointer and a two-pointer technique to find triplets.
- Skip duplicates to avoid repeating triplets.

#### Complexity Analysis

- **Time Complexity:** O(n^2), where `n` is the number of elements in the array. This is due to the sorting of the array and the nested iteration to find triplets.
- **Space Complexity:** O(m), where `m` is the number of unique triplets found. This space is used for storing the triplets and the hash set for duplicate checking.

### Code Snippet

```java
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

                // Adjust the rightPtr while the sum is greater than the target
                while (sum > target && (rightPtr > leftPtr)) {
                    sum = x + nums[leftPtr] + nums[--rightPtr];
                }

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

                    // Skip duplicate elements to the left of rightPtr
                    while (leftPtr < rightPtr && nums[rightPtr] == nums[rightPtr - 1]) {
                        rightPtr--;
                    }

                    // Move to the next unique elements for leftPtr and rightPtr
                    leftPtr++;
                    rightPtr--;
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
```

## Test Cases

Below are the descriptions and expected outcomes for various test cases:

1. **Empty Array**: 
   - Input: `[]`
   - Expected Output: `[]`

2. **Array with No Triplets Summing to Zero**: 
   - Input: `[1, 2, 3, 4]`
   - Expected Output: `[]`

3. **Array with Multiple Duplicate Values**: 
   - Input: `[-1, -1, -1, 2, 2]`
   - Expected Output: `[[-1, -1, 2]]`

4. **Array Where All Elements Are the Same**: 
   - Input: `[0, 0, 0, 0]`
   - Expected Output: `[[0, 0, 0]]`

5. **Array with All Negative Numbers**: 
   - Input: `[-5, -4, -3, -2, -1]`
   - Expected Output: `[]`

6. **Array with All Positive Numbers**: 
   - Input: `[1, 2, 3, 4, 5]`
   - Expected Output: `[]`

7. **Large Array with Random Values**: 
   - Input: `[-2, -1, 0, 1, 1, 2, 3, -3, -4, 5, -6]`
   - Expected Output: Varies (e.g., `[-4, -1, 5]`, `[-3, -2, 5]`)

8. **Array with Zeroes and Positive Numbers**: 
   - Input: `[0, 0, 0, 1, 2, 3]`
   - Expected Output: `[[0, 0, 0]]`

Each test case is executed using the `printTriplets` method, which outputs the resulting list of triplets.

## Usage

To use this solution:

1. Clone the repository.
2. Compile and run the `Main.java` file.
3. Optionally, modify the `main` method to test different arrays.

## Contribution

Contributions to this project are welcome. Please adhere to the following guidelines:

- Ensure new test cases are provided for any additional scenarios.
- Follow Java coding standards and best practices.
- Submit pull requests with clear descriptions of enhancements or fixes.