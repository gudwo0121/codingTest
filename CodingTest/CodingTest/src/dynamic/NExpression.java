package dynamic;

import java.util.HashSet;
import java.util.Set;

public class NExpression {

	// case 1
	static int N = 5;
	static int number = 12;

	// case 2
//	static int N = 2;
//	static int number = 11;

	public static void main(String[] args) {
		NExpression nExpression = new NExpression();
		int answer = nExpression.solution1(N, number);
		System.out.println(answer);
	}

	// solution 1
	// 연산을 통해 나올 수 있는 모든 경우의 수를 담고 비교
	public int solution1(int N, int number) {
		int answer = -1;
		// Set을 기반으로 배열을 만들어 중복 제거
		Set<Integer>[] setArr = new Set[9];

		// 0 ~ 9 자리 만들 수 있는 숫자의 경우의 수 담기
		// ex) null, 5, 55, 555, ... , 555555555
		int t = N;
		for (int i = 1; i < 9; i++) {
			setArr[i] = new HashSet<>();
			setArr[i].add(t);
			t = t * 10 + N;
		}

		// N을 0 ~ 9 번 연산해서 만들 수 있는 모든 경우의 수를 저장
		// ex) 0 번 : null
		// ex) 1 번 : [5]
		// ex) 2 번 : [0, 1, 55, 25, 10]
		// ex) 3 번 : [0, 2, -4, -5, 4, 5, 6, 555, 11, 15, -50, 50, 275, -20, 20, 60, 125, 30]
		// ...
		// ex) 9 번 : [55555555]
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < i; j++) {
				for (Integer a : setArr[j]) {
					for (Integer b : setArr[i - j]) {
						setArr[i].add(a + b);
						setArr[i].add(a - b);
						setArr[i].add(b - a);
						setArr[i].add(a * b);
						if (b != 0) {
							setArr[i].add(a / b);
						}
						if (a != 0) {
							setArr[i].add(b / a);
						}
					}
				}
			}
		}

		// 전체 연산 가능 숫자들 중 처음으로 number를 포함한 인덱스 값 = 최소 사용 횟수
		for (int i = 1; i < 9; i++) {
			if (setArr[i].contains(number)) {
				answer = i;
				break;
			}
		}
		return answer;
	}
}