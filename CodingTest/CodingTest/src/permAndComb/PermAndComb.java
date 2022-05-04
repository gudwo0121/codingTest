package permAndComb;

public class PermAndComb {

	// case 1
	static String[] numbers = { "1", "2", "3", "4", "5" };

	public static void main(String[] args) {
		PermAndComb permAndComb = new PermAndComb();
		permAndComb.solution1(numbers);
	}

	// solution 1
	public void solution1(String[] numbers) {
		int pick = 2;
		String[] combTemp = new String[pick];
		String[] permTemp = new String[pick];
		boolean[] visited = new boolean[numbers.length];

		comb(pick, combTemp, 0, 0);
		System.out.println("=========================");
		perm(pick, permTemp, 0, visited);

	}

	// 순서 고정 조합
	// pick : 뽑고자 하는 개수
	// temp : pick개를 뽑는 결과값을 저장해놓는 배열
	// depth : 현재 개수를 저장해 놓는 값
	// start : 그다음 반복문을 시작하는 값
	public void comb(int pick, String[] temp, int depth, int start) {
		// 조합이 완성되면 출력
		if (pick == depth) {
			for (int i = 0; i < pick; i++) {
				System.out.print(temp[i]);
			}
			System.out.println();
		}
		// 완성되지 않았으면 재귀
		else {
			for (int i = start; i < numbers.length; i++) {
				temp[depth] = numbers[i];
				comb(pick, temp, depth + 1, i + 1);
			}
		}
	}

	// 순서 섞인 순열
	// pick : 뽑고자 하는 개수
	// temp : pick개를 뽑는 결과값을 저장해놓는 배열
	// depth : 현재 개수를 저장해 놓는 값
	// visited : 방문 여부 확인 배열
	public void perm(int pick, String[] temp, int depth, boolean[] visited) {
		// 순열이 완성되면 출력
		if (pick == depth) {
			for (int i = 0; i < pick; i++) {
				System.out.print(temp[i]);
			}
			System.out.println();
		}
		// 완성되지 않았으면 재귀
		else {
			for (int i = 0; i < numbers.length; i++) {
				// 방문 여부가 false
				// 뽑지 않았으면
				if (!visited[i]) {
					// 방문 체크
					visited[i] = true;
					// 해당 값 뽑기
					temp[depth] = numbers[i];
					perm(pick, temp, depth + 1, visited);
					visited[i] = false;
				}
			}
		}
	}

}