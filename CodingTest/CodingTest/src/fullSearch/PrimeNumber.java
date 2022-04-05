package fullSearch;

import java.util.Arrays;

public class PrimeNumber {

	// case 1
	static String numbers = "17";

	// case 2
//	static String numbers = "011";

	public static void main(String[] args) {
		PrimeNumber primeNumber = new PrimeNumber();
		int answer = primeNumber.solution1(numbers);
		System.out.println(answer);
	}

	// solution 1
	public int solution1(String numbers) {
		int answer = 0;
		int[] arr = new int[numbers.length()];

		int i = 0;
		for (String num : numbers.split(""))
			arr[i++] = Integer.parseInt(num);
		System.out.println(Arrays.toString(arr));
		
		
		
		
		

		return answer;
	}
}