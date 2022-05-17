package stackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Printer {

	// case 1
	static int[] priorities = { 2, 1, 3, 2 };
	static int location = 2;

	// case 2
//	static int[] priorities = { 1, 1, 9, 1, 1, 1 };
//	static int location = 0;

	public static void main(String[] args) {
		Printer printer = new Printer();
		int answer = printer.solution1(priorities, location);
		System.out.println(answer);
	}

	// solution 1
	// 값, 인덱스를 기억하는 큐를 각각 선언
	// 삽입, 삭제를 통해 최종적으로 출력되는 인덱스 순서 result에 담기
	public int solution1(int[] priorities, int location) {
		// 우선순위 기록
		Queue<Integer> priority = new LinkedList<>();
		// 인덱스 기록
		Queue<Integer> idx = new LinkedList<>();
		// POP 인덱스 기록
		List<Integer> result = new ArrayList<>();

		// 값과 인덱스(0부터 시작) 담기
		int i = 0;
		for (int pr : priorities) {
			priority.add(pr);
			idx.add(i++);
		}

		// 큐가 비어있으면 중지
		while (!priority.isEmpty()) {

			// 첫번째 값과 인덱스 뽑아 담기
			int target = priority.poll();
			int targetIdx = idx.poll();

			// POP 여부를 판단하는 변수
			boolean isPop = true;
			for (int num : priority) {
				// 값의 우선순위가 MAX : POP
				// 그 외는 SHIFT
				if (num > target)
					isPop = false;
			}

			// SHIFT : 값과 인덱스 맨 뒤로 재배치
			if (!isPop) {
				priority.add(target);
				idx.add(targetIdx);
			}
			// POP : 값의 인덱스 기록
			else {
				result.add(targetIdx);
			}
		}

		// ex) 119111, 0
		// 출력되는 인덱스 순서는 (234501)
		// 여기서 location(=0)은 [4], 그러나 실제 위치는 5번째
		// 따라서 1을 더하고 리턴
		return (result.indexOf(location) + 1);
	}
}