package greedy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LifeBoat {

	// case 1
	static int[] people = { 70, 50, 80, 50 };
	static int limit = 100;

	// case 2
//	static int[] people = { 70, 80, 50 };
//	static int limit = 100;

	public static void main(String[] args) {
		LifeBoat lifeBoat = new LifeBoat();
		int answer = lifeBoat.solution1(people, limit);
		System.out.println(answer);
	}

	// solution 1 = 효율성 100점 (빠름)
	// 양방향에서 좁혀오는 원리 (2가지 방식)
	// 짝이 이루어졌다면 i++, j--
	// ex) (30 40 60 70 80) - (100)
	// 30-70 짝 : 40-80 은 짝이 절대 안되므로 무조건 70 보다 왼쪽일 것
	public int solution1(int[] people, int limit) {
		int answer = 0;
		// 배열 오름차순 정렬
		Arrays.sort(people);

		// 처음 사람과 끝 사람 인덱스 값
		int i = 0;
		int j = people.length - 1;

		// two point 알고리즘 = 속도 1순위
		while (i <= j) {
			int sum = people[i] + people[j];
			if (i != j-- && sum <= limit)
				i++;
			answer++;
		}

		return answer;

//		// for문에 시작값을 명시하지 않는 방법 = 속도 2순위
//		for (; i < j; j--) {
//			if (people[i] + people[j] <= limit)
//				i++;
//		}
//		
//		return people.length - i;
	}

	// solution 2 = 효율성 95점 (아쉽지만 데크를 사용해볼 수 있었음)
	// 데크(Deque)를 활용한 방법
	// 앞뒤 모두 삽입-삭제가 가능하기 때문에 앞과 뒤 하나씩 더해보며 비교
	// List나 Array의 요소를 제거하는 원리는 같으나 삽입-삭제에서 시간복잡도가 더 효율적
	public int solution2(int[] people, int limit) {
		int answer = 0;

		// List를 활용하여 오름차순 정렬
		List<Integer> list = new ArrayList<>();
		for (int kg : people)
			list.add(kg);
		Collections.sort(list);

		// Deque를 활용하여 정렬된 데이터 삽입
		ArrayDeque<Integer> dq = new ArrayDeque<>(50001);
		for (int kg : list)
			dq.add(kg);

		// Deque가 비어있을 때까지 반복
		while (dq.isEmpty() == false) {
			// 가장 몸무게가 많이 나가는 사람 = 마지막 사람 제거 & 담기
			int weight = dq.pollLast();
			// 양방향에서 마지막 사람을 제거해나가며
			// 짝이 맞을 시에는 처음 사람도 제거
			if (dq.isEmpty() == false && weight + dq.peekFirst() <= limit) {
				// 처음 사람 제거
				dq.pollFirst();
			}
			// 마지막 사람만 빠지든, 짝으로 빠지든 보트는 하나씩 추가
			answer++;
		}

		return answer;
	}

}