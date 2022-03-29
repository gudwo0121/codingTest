package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KNumber {

	// case 1
	static int[] array = { 1, 5, 2, 6, 3, 7, 4 };
	static int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };

	public static void main(String[] args) {
		KNumber kNumber = new KNumber();
		int[] answer = kNumber.solution1(array, commands);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1
	// Arrays.copyOfRange 활용
	// 느리지만 가독성이 좋음
	public int[] solution1(int[] array, int[][] commands) {

		int[] answer = new int[commands.length];

		int i = 0;
		for (int[] command : commands) {
			// 커맨드
			int start = command[0];
			int end = command[1];
			int idx = command[2];

			// 1. 자르기
			int[] splited = Arrays.copyOfRange(array, start - 1, end);
			// 2. 정렬하기
			Arrays.sort(splited);
			// 3. k 넘버 찾아 담기
			answer[i++] = splited[idx - 1];
		}

		return answer;
	}

	// solution 2
	// 2중 for문 활용
	// 빠르지만 가독성이 떨어짐
	public int[] solution2(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		ArrayList<Integer> splitList = new ArrayList<>();

		for (int i = 0; i < commands.length; i++) {
			// 리스트 비우기
			splitList.clear();
			for (int j = commands[i][0]; j <= commands[i][1]; j++) {
				// 1. 자르기
				splitList.add(array[j - 1]);
			}
			// 2. 정렬하기
			Collections.sort(splitList);
			// 3. k 넘버 찾아 담기
			answer[i] = splitList.get(commands[i][2] - 1);
		}

		return answer;
	}
}