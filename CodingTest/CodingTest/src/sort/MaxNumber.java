package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MaxNumber {

	// case 1
//	static int[] numbers = { 6, 10, 2 };

	// case 2
//	static int[] numbers = { 3, 30, 34, 5, 9 };

	// case 3
	static int[] numbers = { 1, 1, 0, 0 };

	public static void main(String[] args) {
		MaxNumber maxNumber = new MaxNumber();
		String answer = maxNumber.solution1(numbers);
		System.out.println(answer);
	}

	// solution 1
	public String solution1(int[] numbers) {
		String answer = "";
		HashMap<String, Integer> numbersMap = new HashMap<>();

		int i = 0;
		for (int splited : numbers) {

			String fourDigit = Integer.toString(splited);

			if (fourDigit.length() == 1) {
				fourDigit += fourDigit;
				fourDigit += fourDigit;
				numbersMap.put(fourDigit, i++);
			} else if (fourDigit.length() == 2) {
				fourDigit += fourDigit;
				numbersMap.put(fourDigit, i++);
			} else if (fourDigit.length() == 3) {
				fourDigit += fourDigit.substring(0, 1);
				numbersMap.put(fourDigit, i++);
			} else if (fourDigit.length() == 4) {
				numbersMap.put(fourDigit, i++);
			}
		}

		ArrayList<String> numbersList = new ArrayList<>();
		i = 0;

		for (String fourDigit : numbersMap.keySet()) {
			numbersList.add(fourDigit);
		}
		Collections.sort(numbersList);
		Collections.reverse(numbersList);
		System.out.println(numbersList);

		for (String idx : numbersList)
			answer += numbers[numbersMap.get(idx)];

		return answer;
	}
}