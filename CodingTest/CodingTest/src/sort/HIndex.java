package sort;

import java.util.Arrays;

public class HIndex {

	// case 1
	static int[] citations = { 3, 0, 6, 1, 5 };

	// case 2
//	static int[] citations = { 3, 4, 5, 16, 17, 18, 19, 20 };

	public static void main(String[] args) {
		HIndex hIndex = new HIndex();
		int answer = hIndex.solution1(citations);
		System.out.println(answer);
	}

	// solution 1
	// 하나의 for문 안에서 (논문의 용인 횟수 down) & (용인된 논문의 수 up)
	// 수학기반 논리적 접근방식으로 빠른 시간복잡도를 가진다
	
	// case 1 예시 :
	// 1. 오름차순 정렬 -> (0, 1, 3, 5, 6)
	// 2. 배열을 끝부터 시작까지 역순으로 반복문 실행
	// 3. (i = 6, h = 1) : 6번 인용된 논문 존재 = (6 >= 1) = 1번 이상 인용된 논문이 1편 존재
	// 4. (i = 5, h = 2) : 5번 인용된 논문 존재 = (5 >= 2) = 2번 이상 인용된 논문이 2편 존재
	// 5. (i = 3, h = 3) : 3번 인용된 논문 존재 = (3 >= 3) = 3번 이상 인용된 논문이 3편 존재
	// 6. (i = 1, h = 4) : 1번 인용된 논문 존재 = (1 >= 4) 미성립 = 4번 이상 인용된 논문이 4편 이상 미존재 = 교차점 check!
	// 7. (i = 0, h = 5) : 0번 인용된 논문 존재 = (0 >= 5) 미성립 = 5번 이상 인용된 논문이 5편 이상 미존재
	// 8. 교차점 전, h 값은 3 이다. 
	public int solution1(int[] citations) {
		Arrays.sort(citations);

		int max = 0;
		for (int i = citations.length - 1; i > -1; i--) {
			int min = (int) Math.min(citations[i], citations.length - i);
			if (max < min)
				max = min;
		}
		return max;
	}

	// solution 2
	// 2중 for문 활용
	// 상당히 1차원적이고 느린 방법
	public int solution2(int[] citations) {
		Arrays.sort(citations);

		int answer = -1;
		// 최다 인용 횟수만큼 반복
		for (int i = 0; i <= citations[citations.length - 1]; i++) {
			// i번 이상 인용된 논문의 개수
			int cnt = 0;
			for (int citation : citations) {
				// i번 이상 인용된 논문이 존재한다면
				if (citation >= i)
					cnt++;
			}
			// i번 이상 인용된 논문이 i편 이상 존재한다면
			if (i <= cnt)
				answer = i;
		}
		return answer;
	}
}