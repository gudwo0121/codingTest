package stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class TruckCross {

	// case 1
	static int bridge_length = 2;
	static int weight = 10;
	static int[] truck_weights = { 7, 4, 5, 6 };

	// case 2
//	static int bridge_length = 100;
//	static int weight = 100;
//	static int[] truck_weights = { 10 };

	// case 3
//	static int bridge_length = 100;
//	static int weight = 100;
//	static int[] truck_weights = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

	public static void main(String[] args) {
		TruckCross truckCross = new TruckCross();
		int answer = truckCross.solution1(bridge_length, weight, truck_weights);
		System.out.println(answer);
	}

	// solution 1 연계 클래스
	// 트럭의 Shift 누적을 위한 VO 클래스
	class Truck {
		int weight;
		int move;

		public Truck(int weight) {
			this.weight = weight;
			this.move = 1;
		}

		public void moving() {
			move++;
		}
	}

	// solution 1 = 빠름
	// 초기 진입, 건너기 완료, 무게 비교 후 진입 3가지 상황별로 진행
	// 큐를 2개 활용함으로써 효율성을 높인 방법 (대기 큐 / 이동 큐)
	public int solution1(int bridgeLength, int weight, int[] truckWeights) {
		// 대기중인 트럭
		Queue<Truck> waitQ = new LinkedList<>();
		// 이동중인 트럭
		Queue<Truck> moveQ = new LinkedList<>();

		// 대기중인 트럭 담기
		for (int t : truckWeights) {
			waitQ.offer(new Truck(t));
		}

		int answer = 0;
		int curWeight = 0;

		// 대기중인 혹은 이동중인 트럭이 없을때까지 반복
		while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
			// 1초씩 진행 = 누적 증가
			answer++;

			// 이동중인 트럭이 없다면 = 초기 상태
			if (moveQ.isEmpty()) {
				// 대기중인 트럭에서 뽑기
				Truck t = waitQ.poll();
				// 무게 총합에 누적 증가
				curWeight += t.weight;
				// 뽑은 트럭 이동중인 트럭에 삽입
				moveQ.offer(t);
				continue;
			}

			// 1초씩 이동(SHIFT) 진행
			for (Truck t : moveQ) {
				t.moving();
			}

			// 다리를 모두 건넌 트럭 제거
			// 트럭의 이동(SHIFT) 거리가 길이보다 크다면
			if (moveQ.peek().move > bridgeLength) {
				// 이동중인 트럭에서 제거
				Truck t = moveQ.poll();
				// 무게 총합에서도 감소
				curWeight -= t.weight;
			}

			// 진입 가능 상황
			// 진입 예정 트럭의 무게가 더해져도 제한 무게 이하면 진입
			if (!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
				// 대기중인 트럭에서 뽑기
				Truck t = waitQ.poll();
				// 무게 총합 누적 증가
				curWeight += t.weight;
				// 뽑은 트럭 이동중인 트럭에 삽입
				moveQ.offer(t);
			}
		}

		return answer;
	}

	// solution 2 = 느림
	// 매번 다리위의 트럭 무게 총합을 구해 비교
	// 트럭 추가 진입이 가능하면 무게를 큐에 넣고
	// 추가 진입이 불가하면 "0"을 대신 넣으며 진행
	public int solution2(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		// 다리 위의 트럭 현황
		Queue<Integer> q = new LinkedList<>();

		// 다리 길이에 따라 0으로 큐를 초기화
		for (int i = 0; i < bridge_length; i++)
			q.add(0);

		int i = 0;
		while (true) {
			// 다리 위의 트럭 무게 총합 초기화
			// 트럭 현황이 계속 바뀌므로 초기화 필요
			int total = 0;
			// 마지막 트럭이 다리에 진입했다면
			// 해당 트럭이 다리를 건너면 종료 = 다리 길이만큼 진행 횟수 추가 = 종료
			if (i == truck_weights.length) {
				answer += bridge_length;
				break;
			}
			// 1초 진행 = SHIFT
			q.remove();
			answer++;

			// 트럭 무게 총합 계산
			for (int wei : q)
				total += wei;

			// 무게 총합에 진입 예정 트럭의 무게를 합친 값이 제한 무게보다 작다면
			if (total + truck_weights[i] <= weight) {
				// 트럭 진입
				q.add(truck_weights[i]);
				// 커서는 다음 트럭을 가르킴
				i++;
			} else {
				// 제한 무게보다 크다면 진입 불가 = 0 삽입
				q.add(0);
			}
		}

		return answer;
	}

}