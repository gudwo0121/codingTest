package dynamic;

public class IntTriangle {

	// case 1
	static int[][] triangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };

	public static void main(String[] args) {
		IntTriangle intTriangle = new IntTriangle();
		int answer = intTriangle.solution1(triangle);
		System.out.println(answer);
	}

	// solution 1
	// 삼각형 맨 아래 층부터 역으로 올라오며 Max값을 구하는 방식
	// Bottom-up Dynamic Programming
	public int solution1(int[][] triangle) {
		int answer = 0;

		// 삼각형의 계층 (감소 = 아래서 위로)
		for (int i = triangle.length - 1; i >= 1; i--) {
			// 계층 내 더해질 수 있는 수
			for (int j = 1; j < i + 1; j++) {
				// 그 중에 더 큰 수를 상위 계층에 덮어씌우기
				int a = triangle[i - 1][j - 1] + triangle[i][j - 1];
				int b = triangle[i - 1][j - 1] + triangle[i][j];
				triangle[i - 1][j - 1] = Math.max(a, b);
				// 최종적으로 더해진 값이 최대 값
				answer = Math.max(a, b);
			}
		}
		return answer;
	}
}