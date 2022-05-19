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

	// solution 1
	public int solution1(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < bridge_length; i++)
			q.add(0);

		int i = 0;
		int total = 0;
		while (!q.isEmpty()) {
			q.remove();

			for (int wei : q)
				total += wei;

			if (total <= weight) {
				q.add(truck_weights[i]);
				i++;
			} else {
				q.add(0);
			}
			
			System.out.println(q);

		}

		return answer;
	}
}