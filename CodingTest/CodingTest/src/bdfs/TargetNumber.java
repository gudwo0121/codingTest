package bdfs;

public class TargetNumber {

	// case 1
	static int[] numbers = { 1, 1, 1, 1, 1 };
	static int target = 3;

	// case 2
//	static int[] numbers = { 4, 1, 2, 1 };
//	static int target = 2;

	public static void main(String[] args) {
		TargetNumber targetNumber = new TargetNumber();
		int answer = targetNumber.solution1(numbers, target);
		System.out.println(answer);
	}

	// solution 1
	// (+ ) -> (++ ) -> (+++ ) -> (++++) ->
	// (++++) -> (+++-) ->
	// (++-+) -> (++--) ->
	// (+-++) -> (+-+-) -> (+--+) -> (+---) ->
	// (-+++) -> (-++-) -> (-+-+) -> (-+--) -> (--++) -> (--+-) -> (---+) -> (----)
	public int solution1(int[] numbers, int target) {
		int answer = 0;
		// 깊이와 총합은 0 부터 시작
		answer = dfs(0, 0, numbers, target);
		return answer;
	}

	// 모든 경우를 DFS로 2^(배열길이)번 반복 = 스택 + 재귀
	// 그 중에서 타겟과 같은 횟수를 누적하여 리턴
	// DFS란 : 끝까지 전진 -> 막히면 한발 후퇴 -> 다른 곳으로 다시 끝까지 전진 = 재귀 함수의 스택화
	public int dfs(int depth, int total, int[] numbers, int target) {

		// 깊이가 끝까지 도달했고
		if (depth == numbers.length) {
			// 누적합이 타겟과 같다면
			if (total == target) {
				// 1을 리턴하라 = 리턴된 1들의 합이 answer로 리턴된다
				return 1;
			}
			// 누적합이 타겟과 다르다면 0을 리턴하라
			// Ex) 하단에 있는 재귀함수의 전자였다면 : (++++) >리턴> (+++) >실행> (+++-)
			// Ex) 하단에 있는 재귀함수의 후자였다면 : (+++-) >리턴> (+++) >리턴> (++-) >실행> (++-+)
			return 0;
		}

		// (+)를 덧붙이는건 전자
		// (-)를 덧불이는건 후자
		return dfs(depth + 1, total + numbers[depth], numbers, target)
				+ dfs(depth + 1, total - numbers[depth], numbers, target);
	}
}