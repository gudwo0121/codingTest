package greedy;

import java.util.Arrays;

public class ConnectingLands {

	// case 1
	static int n = 5;
	static int[][] costs = { { 0, 1, 5 }, { 1, 2, 3 }, { 2, 3, 3 }, { 3, 1, 2 }, { 3, 0, 4 }, { 2, 4, 6 },
			{ 4, 0, 7 } };
	// parent array for Union & Find
	static int[] parent = new int[n];

//	5 [[0, 1, 5], [1, 2, 3], [2, 3, 3], [3, 1, 2], [3, 0, 4], [2, 4, 6], [4, 0, 7]] = 15 ???? 안됌
//	5 [[0, 1, 1], [3, 4, 1], [1, 2, 2], [2, 3, 4]] = 8
//	4 [[0, 1, 5], [1, 2, 3], [2, 3, 3], [1, 3, 2], [0, 3, 4]] = 9
//	5 [[0, 1, 1], [3, 1, 1], [0, 2, 2], [0, 3, 2], [0, 4, 100]] = 104
//	6 [[0, 1, 5], [0, 3, 2], [0, 4, 3], [1, 4, 1], [3, 4, 10], [1, 2, 2], [2, 5, 3], [4, 5, 4]] = 11
//	5 [[0, 1, 1], [2, 3, 1], [3, 4, 2], [1, 2, 2], [0, 4, 100]] = 6
//	5 [[0, 1, 1], [0, 4, 5], [2, 4, 1], [2, 3, 1], [3, 4, 1]] = 8
//	5 [[0, 1, 1], [0, 2, 2], [0, 3, 3], [0, 4, 4], [1, 3, 1]] = 8

	public static void main(String[] args) {
		ConnectingLands connectingLands = new ConnectingLands();
		int answer = connectingLands.solution1(n, costs);
		System.out.println(answer);
	}

	// Find Parent
	public int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

	// Union Parent
	public void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b)
			parent[b] = a;
	}

	// solution 1
	// Kruskal + Union & Find 알고리즘을 이용한 방법
	public int solution1(int n, int[][] costs) {
		int answer = 0;
		// parent array 초기화
		for (int i = 0; i < n; i++)
			parent[i] = i;

		for (int i = 0; i < costs.length; i++) {
			if (parent[costs[i][1]] != 0) {
				answer += costs[i][2];
				union(costs[i][0], costs[i][1]);
				System.out.println(costs[i][0] + " / " + costs[i][1] + " / " + costs[i][2]);
				System.out.println(Arrays.toString(parent));
			}
		}
		return answer;
	}
}