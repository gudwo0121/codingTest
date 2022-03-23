package hash;

import java.util.Arrays;
import java.util.HashMap;

public class NotArrivedRunner {

	// case 1
	static String[] participant = { "leo", "kiki", "eden" };
	static String[] completion = { "eden", "kiki" };

	// case 2
//	static String[] participant = { "marina", "josipa", "nikola", "vinko", "filipa" };
//	static String[] completion = { "josipa", "filipa", "marina", "nikola" };

	// case 3
//	static String[] participant = { "mislav", "stanko", "mislav", "ana" };
//	static String[] completion = { "stanko", "ana", "mislav" };

	public static void main(String[] args) {
		NotArrivedRunner notArrivedRunner = new NotArrivedRunner();
		String answer = notArrivedRunner.solution2(participant, completion);
		System.out.println(answer);
	}

	// solution 1
	// 정렬 후 비교
	// 완주자가 1명이라는 조건이 아니거나 응용이 된다면 골치아파질 수 있다.
	public String solution1(String[] participant, String[] completion) {

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

	// solution 2
	// key & value로 해결
	// 주자 카운트 추가 -> 완주자 카운트 감소 -> 미완주자만 1 남음 -> key값 출력
	public String solution2(String[] participant, String[] completion) {

		String answer = "";

		HashMap<String, Integer> map = new HashMap<>();

		// 상향 for문 활용
		// for (1차원 문자열 : 2차원 문자열 배열)
		// for (String str : arr)
		// = for (String str = 0; str < arr ; str++)

		// 주자 : 주자 명수 = 주자 정보 담기
		// ex) leo : 1
		// ex) leo : 2 (중복 나오면 +1)
		for (String runner : participant)
			map.put(runner, map.getOrDefault(runner, 0) + 1);

		// 주자 : 주자 명수 = 주자 목록에서 완주자 빼기
		// ex) leo : 1
		// ex) leo : 0 (완주자 목록에 있다면 -1)
		for (String runner : completion)
			map.put(runner, map.get(runner) - 1);

		// map.keySet() : length와 비슷한데 키값에 따라 반복3
		// 미완주자가 다수라면 answer 배열로 담으면 가능
		for (String key : map.keySet())
			if (map.get(key) != 0) {
				answer = key;
			}

		return answer;
	}
}