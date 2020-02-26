import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Determine if the two sets of integers are permutation of each other.
 */
public class Solution {

  /**
   * Determine if these arrays contain a permutation of each other.
   * Use the arrays O(n log(n))
   */
  static boolean isPermutationA(int[] arr1, int[] arr2) {

    // **** check array dimensions -> O(1) ****
    if (arr1.length != arr2.length) {
      return false;
    }

    // **** sort the arrays (Quicksort: Ω(n log(n)) to O(n^2)) ****
    Arrays.sort(arr1);
    Arrays.sort(arr2);

    // // **** traverse the arrays checking if they do not match O(n) ****
    // for (int i = 0; i < arr1.length; i++) {
    //   if (arr1[i] != arr2[i]) {
    //     return false;
    //   }
    // }

    // **** these are permutations ****
    return Arrays.equals(arr1, arr2);
  }

  /**
   * Determine if these arrays contain a permutation of each other.
   * Use a hash map O(n)
   */
  static boolean isPermutationH(int[] arr1, int[] arr2) {

    // **** check array dimensions -> O(1) ****
    if (arr1.length != arr2.length) {
      return false;
    }

    // **** hash map with key counts ****
    HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

    // **** load hash map from first array -> O(n) ****
    for (int i = 0; i < arr1.length; i++) {
      if (hm.get(arr1[i]) == null) {
        hm.put(arr1[i], 1);
      } else {
        int value = hm.get(arr1[i]);
        hm.put(arr1[i], ++value);
      }
    }

    // **** traverse second array removing elements from the hashmap -> O(n) ****
    for (int i = 0; i < arr2.length; i++) {

      // **** check if key is in the hash map ****
      if (hm.containsKey(arr2[i])) {
        int value = hm.get(arr2[i]);
        if (value == 1) {
          hm.remove(arr2[i]);
        } else {
          value -= 1;
          hm.put(arr2[i], value);
        }
      } else {
        return false;
      }

    }

    // **** these are permutations ****
    return true;
  }

  /**
   * Determine if these arrays contain a permutation of each other.
   * Use Arrays class O(1).
   * Depending if you are allowed you may use the Arrays.equal() method.
   * In practice you would avoid calling this function and you using the Arrays method.
   */
  static boolean isPermutationE(int[] arr1, int[] arr2) {
    return Arrays.equals(arr1, arr2);
  }

  /**
   * Test scaffolding.
   * 
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    
    // **** open a buffered reader **** 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // **** read the first set of numbers ****
    int[] arr1 = Arrays.stream(br.readLine().split(" "))
                  .mapToInt(Integer::parseInt)
                  .toArray();

    // **** read the second set of numbers ****
    int[] arr2 = Arrays.stream(br.readLine().split(" "))
                  .mapToInt(Integer::parseInt)
                  .toArray();

    // **** determine if the sets of integers are permutations of each other (using Arrays class) ****
    System.out.println("isPermutationA: " + isPermutationA(arr1, arr2));

    // **** determine if the sets are permutations of each other (using a hash map) ****
    System.out.println("isPermutationH: " + isPermutationH(arr1, arr2));

    // **** determine if the sets are permutations of each other (using Arrays class) ****
    System.out.println("isPermutationE: " + isPermutationE(arr1, arr2));

    // **** close the buffered reader ****
    br.close();
  }

}