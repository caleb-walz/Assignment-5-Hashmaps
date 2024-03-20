import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Assignment5 {

	// Question 5: Radix Sort
	// time: O(m*n), where m = max length of string in s and n = s.length
	// space: O(m*n), where m = max length of string in s and n = s.length
	public static String[] radixSort(String[] s) {
		int maxLength = -1;
		for (String str : s) {
			if (str.length() > maxLength) maxLength = str.length();
		}

		HashMap<Integer, LinkedList<String>> buckets = new HashMap<>();
		int curChar = 0; // current char from end of string
		for (int ch = 0; ch < maxLength; ch++) {
			for (int i = 0; i < 52; i++) {
				buckets.put(i, new LinkedList<>());
			}
			for (int i = 0; i < s.length; i++) {
				String str = s[i];
				int bucketIndex;
				if (str.length() - 1 - curChar >= 0) {
					bucketIndex = charValue(str.charAt(str.length() - 1 - curChar)) % 52;
				} else {
					bucketIndex = charValue(str.charAt(0)) % 52;
				}
				buckets.get(bucketIndex).add(str);
			}
			int arrayIndex = 0;
			for (int i = 0; i < 52; i++) {
				for (int j = 0; j < buckets.get(i).size(); j++) {
					s[arrayIndex] = buckets.get(i).get(j);
					arrayIndex++;
				}
			}
			curChar++;
		}

		System.out.println(Arrays.toString(s));
		return s;
	}

	// Question 5: Radix Sort
	// helper method
	private static int charValue(char c) {
		int val;
		if (c <= 90) { // uppercase letter
			val = (c - 65) * 2; // A = 0, B = 2, C = 4, ... , Y = 48, Z = 50
		} else { // lowercase letter
			val = ((c - 96) * 2) - 1; // a = 1, b = 3, c = 5, ... , y = 49, z = 51
		}
		return val;
	}

	// Question 6: Sub-integer
	// time: O(n), where n = t.length
	// space: O(n), where n = t.length
	public static String isSubset(int[] s, int[] t) {
		if (s.length > t.length) return "No"; // no duplicates

		HashSet<Integer> table = new HashSet<>();
		for (int i : t) table.add(i);
		for (int i : s) {
			if(table.add(i)) return "No";
		}

		return "Yes";
	}

	public static void main(String[] args) {
		System.out.println("Testing Radix Sort");
		radixSort(new String[]{ "gojo", "google", "jogo", "bill", "pup", "cipher", "watchmen", "knight",
				"it", "stand", "sandman", "hydra", "surtr" });
		radixSort(new String[]{ "Gojo", "google", "jogo", "bill", "Bup", "cipher", "watchmen", "knight",
				"it", "Stand", "sandman", "hydra", "Surtr" });
		System.out.println();

		System.out.println("Testing Sub-integer");
		int[] s = new int[]{ 32, 3 };
		int[] t = new int[]{ 1, 2, 3, 52, 32, 54 };
		System.out.println(isSubset(s, t));
		s = new int[]{ 89, 32, 54, 32, 3 };
		t = new int[]{ 54, 32, 99 };
		System.out.println(isSubset(s, t));
		s = new int[]{ 0, 67 };
		t = new int[]{ 100, 5, 66, 2, 32, 90 };
		System.out.println(isSubset(s, t));
		s = new int[]{};
		t = new int[]{ 54, 32, 99 };
		System.out.println(isSubset(s, t));
	}

}
