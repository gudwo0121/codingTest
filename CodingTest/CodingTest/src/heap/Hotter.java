package heap;

import java.util.PriorityQueue;

public class Hotter {

	// case 1
	static int[] scoville = { 1, 2, 3, 9, 10, 12 };
	static int K = 7;

	// case 2
//	static int[] scoville = { 1, 2 };
//	static int K = 7;

	public static void main(String[] args) {
		Hotter hotter = new Hotter();
		int answer = hotter.solution1(scoville, K);
		System.out.println(answer);
	}

	// solution 1
	// 우선순위 큐를 활용한 방법 = ArrayList로 하면 효율성 탈락
	// 우선순위 큐 : 삽입, 삭제 발생 시 max, min 값을 지속적으로 재정의 해주는 상황에 활용
	// minHeap(root가 최소값), maxHeap(root가 최대값)
	public int solution1(int[] scoville, int K) {
		int answer = 0;
		// minHeap 구조를 가지는 우선순위 큐 선언
		// maxHeap 구조를 가지려면 PriorityQueue<>(Collections.reverseOrder())로 변경
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 우선순위 큐에 minHeap으로 스코빌지수 값 담기
		for (int scov : scoville)
			pq.add(scov);

		// break 조건을 만족할 때까지 무한 루프
		while (true) {
			// 어떠한 경우로도 K 이상의 스코빌을 만들지 못하는 경우
			if (pq.size() == 1 && pq.peek() < K) {
				answer = -1;
				break;
			}
			// minHeap이므로 처음값(=최소값)이 K 이상이면 섞기 종료
			if (pq.peek() >= K) {
				break;
			}
			// 처음값(=최소값) 빼고 담기
			int s1 = pq.poll();
			// 처음값(=두번째 최소값) 빼고 담기
			// 이미 최소값을 뺐기 때문에 다시 정의된 최소값이 두번째 최소값임
			int s2 = pq.poll() * 2;
			// 섞은 스코빌 지수를 큐에 새로 담기
			pq.add(s1 + s2);
			// 섞은 횟수 누적 증가
			answer++;
		}
		return answer;
	}
}