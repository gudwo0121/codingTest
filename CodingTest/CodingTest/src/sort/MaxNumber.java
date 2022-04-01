package sort;

import java.util.Arrays;
import java.util.Comparator;

public class MaxNumber {

	// case 1
	static int[] numbers = { 6, 10, 2 };

	// case 2
//	static int[] numbers = { 3, 30, 34, 5, 9 };

	// case 3
//	static int[] numbers = { 1, 10, 100, 1000 };

	public static void main(String[] args) {
		MaxNumber maxNumber = new MaxNumber();
		String answer = maxNumber.solution1(numbers);
		System.out.println(answer);
	}

	// solution 1
	// Comparator-compare 활용
	// StringBuilder 활용
	// solution 2
	public String solution1(int[] numbers) {
		String answer = "";
		String[] strArr = new String[numbers.length];

		// 숫자 배열 to 문자열 배열
		int i = 0;
		for (int number : numbers) {
			strArr[i++] = Integer.toString(number);
		}

		// Comparator를 사용하면 compare 메소드 기준으로 모든 조합을 비교해서
		// 오름차 혹은 내림차 순으로 정렬해준다
		// 문자열 배열의 특정 기준 정렬에 매우 유용하므로 기억해두자
		Arrays.sort(strArr, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return (str2 + str1).compareTo(str1 + str2);
			}
		});

		// 문자열 배열 요소들 모두 합치기
		// answer += sortedNum 보다
		// StringBuilder 사용하는 것이 시간복잡도를 훨씬 줄일 수 있다
		StringBuilder sd = new StringBuilder();
		for (String sortedNum : strArr)
			sd.append(sortedNum);
		answer = sd.toString();

		// "0000" to "0"
		if (answer.startsWith("0"))
			return "0";

		return answer;
	}
}