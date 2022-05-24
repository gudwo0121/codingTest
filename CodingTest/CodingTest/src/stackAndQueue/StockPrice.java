package stackAndQueue;

import java.util.Arrays;

public class StockPrice {

	// case 1
	static int[] prices = { 1, 2, 3, 2, 3 };

	public static void main(String[] args) {
		StockPrice stockPrice = new StockPrice();
		int[] answer = stockPrice.solution1(prices);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1
	// 2중 for문을 활용한 방법
	public int[] solution1(int[] prices) {
		int[] answer = new int[prices.length];

		// i++번째 부터 끝까지 비교 반복
		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				// 1초씩 선 증가
				answer[i] += 1;
				// 주가가 떨어지면 후 탈출
				if (prices[i] > prices[j])
					break;
			}
		}
		return answer;
	}
}