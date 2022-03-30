package hash;

import java.util.HashMap;

public class Camouflage {

	// case 1
	static String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
			{ "green_turban", "headgear" } };

	// case 2
//	static String[][] clothes = { { "crowmask", "face" }, { "bluesunglasses", "face" }, { "smoky_makeup", "face" } };

	public static void main(String[] args) {
		Camouflage camouflage = new Camouflage();
		int answer = camouflage.solution1(clothes);
		System.out.println(answer);
	}

	// solution 1
	// 경우의 수 규칙을 먼저 파악하고 문제를 풀어야 한다
	// 조합 x 개수와 경우의 수 규칙 o
	public int solution1(String[][] clothes) {
		int answer = 1;
		// (의상 종류 : 종류별 의상 개수)
		HashMap<String, Integer> closet = new HashMap<>();

		for (int i = 0; i < clothes.length; i++) {
			// 의상 종류
			String key = clothes[i][1];
			// key가 있으면 +1 증가
			// key가 없으면 1 입력
			closet.put(key, closet.getOrDefault(key, 0) + 1);
		}

		for (int caseCount : closet.values())
			// 조합의 모든 경우의 수 = (종류별 의상 개수 + 1)을 모두 곱한 결과
			// (+1) 하는 이유는 안 입었을 경우를 포함하기 위함
			answer *= caseCount + 1;

		// 하나도 안입는 경우는 제외하므로 -1
		return (answer - 1);
	}

}
