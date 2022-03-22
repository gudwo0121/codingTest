package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GymSuit {

	// case 1
//	static int n = 5;
//	static int[] lost = { 2, 4 };
//	static int[] reserve = { 1, 3, 5 };

	// case 2
//	static int n = 5;
//	static int[] lost = { 2, 4 };
//	static int[] reserve = { 3 };

	// case 3
//	static int n = 3;
//	static int[] lost = { 3 };
//	static int[] reserve = { 1 };

	// case 4
	static int n = 5;
	static int[] lost = { 1, 2, 4 };
	static int[] reserve = { 2, 4, 5 };

	public static void main(String[] args) {
		GymSuit gymSuit = new GymSuit();
		int answer = gymSuit.solution2(n, lost, reserve);
		System.out.println(answer);
	}

	// solution 1
	public int solution1(int n, int[] lost, int[] reserve) {
		int answer = n - lost.length;

		ArrayList<Integer> lostArr = new ArrayList<>();
		ArrayList<Integer> reserveArr = new ArrayList<>();
		ArrayList<Integer> tempArr = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();

		Arrays.sort(lost);
		Arrays.sort(reserve);

		for (int i : lost)
			lostArr.add(i);
		for (int i : reserve)
			reserveArr.add(i);

		// 중복 값을 담는 리스트 = 여벌이 있지만 하나 잃어버린 사람
		for (int i : lost)
			tempArr.add(i);
		tempArr.retainAll(reserveArr);
		answer += tempArr.size();

		lostArr.removeAll(tempArr);
		reserveArr.removeAll(tempArr);

		for (int i : reserveArr)
			for (int j : lostArr)
				if (j + 1 == i || j - 1 == i) {
					if (!map.containsValue(j) && !map.containsKey(i)) {
						map.put(i, j);
						answer++;
					}
				}

		return answer;
	}

	// solution 2
	public int solution2(int n, int[] lost, int[] reserve) {
		int answer = n;
		int[] student = new int[n];

		for (int i : lost) {
			student[i - 1]--;
			for (int j = 0; j < student.length; j++) {
				System.out.print(student[j] + " , ");
			}
			System.out.println();
		}

		System.out.println();
		for (int i : reserve) {
			student[i - 1]++;
			for (int j = 0; j < student.length; j++) {
				System.out.print(student[j] + " , ");
			}
			System.out.println();
		}

		return answer;
	}

}