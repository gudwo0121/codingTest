package fullSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MockExam {

	// case 1
//	static int[] answers = { 1, 2, 3, 4, 5 };

	// case 2
	static int[] answers = { 1, 3, 2, 4, 2 };

	// case 3
//	static int[] answers = { 2, 1, 2, 3, 2 };

	// 수포자들의 찍기 패턴들
	static int[][] patterns = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 }, { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };

	public static void main(String[] args) {
		MockExam mockExam = new MockExam();
		int[] answer = mockExam.solution1(answers);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1
	public int[] solution1(int[] answers) {
		ArrayList<Integer> correctList = new ArrayList<>();
		ArrayList<Integer> answerList = new ArrayList<>();

		for (int i = 0; i < patterns.length; i++) {
			correctList.add(grading(answers, i));
		}

		answerList.add(0, correctList.indexOf(Collections.max(correctList)) + 1);
		if (correctList.get(1) == correctList.get(0)) {
			answerList.add(1, 2);
		}
		if (correctList.get(2) == correctList.get(0)) {
			answerList.add(2, 3);
		}
		
		int[] answer = new int[answerList.size()];
		int size = 0;
		for (int temp : answerList)
			answer[size++] = temp;

		return answer;
	}

	public int grading(int[] answers, int studentNum) {
		int count = 0;
		int correct = 0;

		for (int i = 0; i < answers.length; i++) {
			if (count == 5) {
				count = 0;
			}
			if (answers[i] == patterns[studentNum][count++]) {
				correct++;
			}
		}
		return correct;
	}

}