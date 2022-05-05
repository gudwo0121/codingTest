package fullSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carpet {

	// case 1
	static int brown = 10;
	static int yellow = 2;

	// case 2
//	static int brown = 8;
//	static int yellow = 1;

	// case 3
//	static int brown = 24;
//	static int yellow = 24;

	// case 4
//	static int brown = 18;
//	static int yellow = 6;

	public static void main(String[] args) {
		Carpet carpet = new Carpet();
		int[] answer = carpet.solution1(brown, yellow);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1 = 속도 1순위
	// (x-2) * (y-2) = yellow
	// (x * y) - yellow = brown
	// 수식만을 활용한 방법
	public int[] solution1(int brown, int yellow) {
		int a = (brown + 4) / 2;
		int total = brown + yellow;
		int[] answer = { (int) (a + Math.sqrt(a * a - 4 * total)) / 2, (int) (a - Math.sqrt(a * a - 4 * total)) / 2 };
		return answer;
	}

	// solution 2 = 속도 2순위
	// 약수를 담은 리스트의 처음과 끝을 곱해 brown의 개수 체크
	// 짝이 맞으면 전체 크기 계산하는 방법
	public int[] solution2(int brown, int yellow) {
		// 전체 세로, 가로를 담을 배열
		int[] answer = new int[2];
		// yellow 약수를 담은 리스트
		List<Integer> div = new ArrayList<>();

		// yellow의 약수를 리스트에 담기
		for (int i = 1; i <= yellow; i++) {
			if (yellow % i == 0) {
				div.add(i);
			}
		}

		// 노랑 개수 = (약수 처음++) * (약수 끝--)
		// 갈색 개수 = (2*노랑가로) + (2*노랑세로) + 4
		// 위 수식을 활용하여 짝이 맞는 경우를 도출
		// 해당 경우 +2 값이 전체 크기
		for (int i = 0; i < div.size(); i++) {
			// 노랑 세로 * 2
			int y = div.get(i) * 2;
			// 노랑 가로 * 2
			int x = div.get(div.size() - i - 1) * 2;

			// 갈색 개수와 맞아 떨어지면 노랑 개수 +2
			// 전체 크기 answer에 담기
			if (x + y + 4 == brown) {
				// 전체 세로
				answer[0] = x / 2 + 2;
				// 전체 가로
				answer[1] = y / 2 + 2;
				// 찾으면 break = 효율성
				break;
			}
		}
		return answer;
	}
}