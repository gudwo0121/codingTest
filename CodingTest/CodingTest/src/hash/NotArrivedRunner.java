package hash;

import java.util.Arrays;

public class NotArrivedRunner {

	// case 1
//	static String[] participant = { "leo", "kiki", "eden" };
//	static String[] completion = { "eden", "kiki" };

	// case 2
//	static String[] participant = { "marina", "josipa", "nikola", "vinko", "filipa" };
//	static String[] completion = { "josipa", "filipa", "marina", "nikola" };

	// case 3
	static String[] participant = { "mislav", "stanko", "mislav", "ana" };
	static String[] completion = { "stanko", "ana", "mislav" };

	public static void main(String[] args) {
		NotArrivedRunner notArrivedRunner = new NotArrivedRunner();
		String answer = notArrivedRunner.solution(participant, completion);
		System.out.println(answer);
	}

	// solution 1
	// 정렬 후 비교
	// 완주자가 1명이라는 조건이 아니거나 응용이 된다면 골치아파질 수 있다.
	public String solution(String[] participant, String[] completion) {

		// 문자열 배열 정렬
		Arrays.sort(participant);
		Arrays.sort(completion);

		// i를 밖으로 빼서 선언함으로 마지막값이 미완주자(정답)일 경우 처리 가능
		int i;
		for (i = 0; i < completion.length; i++) {
			if (!participant[i].equals(completion[i])) {
				return participant[i];
			}
		}
		return participant[i];
	}
}