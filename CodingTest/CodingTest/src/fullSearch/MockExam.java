package fullSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MockExam {

	// case 1
	static int[] answers = { 1, 2, 3, 4, 5 };

	// case 2
//	static int[] answers = { 1, 3, 2, 4, 2 };

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
	// 빠름
	public int[] solution1(int[] answers) {
		HashMap<Integer, Integer> scoreMap = new HashMap<>();
		// 갱신용 변수
		int max = -1;

		// 모범답안의 정답 개수만큼 반복
		for (int i = 0; i < answers.length; i++) {

			// 모범답안이 수포자 1의 답과 같다면
			// 5개 단위로 패턴 반복 = [i % 5]
			if (answers[i] == patterns[0][i % 5]) {
				// (1 : 기존값 + 1증가) = 기존값 없으면 0으로 시작
				scoreMap.put(1, scoreMap.getOrDefault(1, 0) + 1);
			}

			// 모범답안이 수포자 2의 답과 같다면
			// 8개 단위로 패턴 반복 = [i % 8]
			if (answers[i] == patterns[1][i % 8]) {
				// (1 : 기존값 + 1증가) = 기존값 없으면 0으로 시작
				scoreMap.put(2, scoreMap.getOrDefault(2, 0) + 1);
			}

			// 모범답안이 수포자 3의 답과 같다면
			// 10개 단위로 패턴 반복 = [i % 10]
			if (answers[i] == patterns[2][i % 10]) {
				// (1 : 기존값 + 1증가) = 기존값 없으면 0으로 시작
				scoreMap.put(3, scoreMap.getOrDefault(3, 0) + 1);
			}
		}

		// 수포자 명단
		ArrayList<Integer> supoList = new ArrayList<>();

		// 맞춘 수포자들 중
		for (int key : scoreMap.keySet()) {

			// 더 많은 개수를 맞춘 수포자라면
			if (max < scoreMap.get(key)) {
				// max 값 갱신 후, 수포자 번호를 담는다
				max = scoreMap.get(key);
				supoList.clear();
				supoList.add(key);
			}
			// 똑같은 개수를 맞춘 수포자라면
			else if (max == scoreMap.get(key)) {
				supoList.add(key);
			}
		}

		// 수포자 명단 오름차순 정렬
		Collections.sort(supoList);
		// 수포자 명단 길이에 맞게 가변크기 선언
		int[] answer = new int[supoList.size()];

		// 정렬된 수포자 명단 List to Array
		int i = 0;
		for (int supo : supoList)
			answer[i++] = supo;

		return answer;
	}

	// solution 2
	// 느림
	public int[] solution2(int[] answers) {
		// 수포자들의 패턴
		int[] supo1 = { 1, 2, 3, 4, 5 };
		int[] supo2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] supo3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
		// 수포자들의 정답 개수
		int[] score = new int[3];

		// 모범답안과 비교 후, 맞춘 개수 누적 증가
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == supo1[i % supo1.length]) {
				score[0]++;
			}
			if (answers[i] == supo2[i % supo2.length]) {
				score[1]++;
			}
			if (answers[i] == supo3[i % supo3.length]) {
				score[2]++;
			}
		}

		// 수포자들 중에서 가장 많이 맞춘 개수
		int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
		// 가장 많이 맞춘 수포자 명단
		ArrayList<Integer> supoList = new ArrayList<>();

		// 동일 개수여도 max가 같아 명단에 추가 가능
		if (maxScore == score[0]) {
			supoList.add(1);
		}
		if (maxScore == score[1]) {
			supoList.add(2);
		}
		if (maxScore == score[2]) {
			supoList.add(3);
		}

		// 명단 list to array로 리턴
		return supoList.stream().mapToInt(i -> i.intValue()).toArray();
	}
}