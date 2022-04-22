package greedy;

import java.util.Stack;

public class MakeBigNum {

	// case 1
	static String number = "1924";
	static int k = 2;

	// case 2
//	static String number = "1231234";
//	static int k = 3;

	// case 3
//	static String number = "4177252841";
//	static int k = 4;

	public static void main(String[] args) {
		MakeBigNum makeBigNum = new MakeBigNum();
		String answer = makeBigNum.solution1(number, k);
		System.out.println(answer);
	}

	// solution 1 = 매우 빠름
	// stack을 활용한 풀이
	public String solution1(String number, int k) {
		// 총길이 - 삭제개수 = 만들어야할 큰 수의 자릿수
		char[] result = new char[number.length() - k];
		// 숫자를 하나씩 담아 비교할 스택 선언
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < number.length(); i++) {
			// number 한자리씩 스택에 담기 위한 변수
			char c = number.charAt(i);

			// (다음 숫자) vs (스택에 최근순 들어온 숫자) 비교
			// 전자가 더 크면 후자 pop (동시에 k값 감소)
			// 후자가 더 크면 전자 push
			// 모두 삭제완료 시, 즉, k가 0이 되면 나머지는 모두 push 처리

			// ex) 4177252841
			// 4 -> [4]
			// 1 -> [4, 1]
			// 1 삭제 , 남은 삭제 : 3
			// 4 삭제 , 남은 삭제 : 2
			// 7 -> [7]
			// 7 -> [7, 7]
			// 2 -> [7, 7, 2]
			// 2 삭제 , 남은 삭제 : 1
			// 5 -> [7, 7, 5]
			// 2 -> [7, 7, 5, 2]
			// 2 삭제 , 남은 삭제 : 0
			// 8 -> [7, 7, 5, 8]
			// 4 -> [7, 7, 5, 8, 4]
			// 1 -> [7, 7, 5, 8, 4, 1]
			while (!stack.isEmpty() && stack.peek() < c && k-- > 0)
				stack.pop();
			stack.push(c);
		}

		// result에 stack 요소 담기
		for (int i = 0; i < result.length; i++) {
			result[i] = stack.get(i);
		}

		// char[] 인 result를 String으로 반환하는 법
		return new String(result);
	}

	// solution 2 = 매우 느림
	// 인덱스를 활용한 풀이 (인덱스 범위별 비교)
	public String solution2(String number, int k) {
		String answer = "";
		// 한자리씩 담은 배열
		int[] arr = new int[number.length()];
		// 인덱스 범위별 가장 큰 수의 인덱스를 기억할 변수
		int idx = -1;
		// 만들어야할 자릿수
		int len = number.length() - k;

		// 주어진 숫자 잘라서 배열에 담기
		int i = 0;
		for (String num : number.split(""))
			arr[i++] = Integer.parseInt(num);

		// 만들어야할 자릿수를 모두 만들때까지 반복
		while (len != 0) {
			// 인덱스 범위별 가장 큰 수를 판단하기 위해 매번 '-1'로 초기화
			int max = -1;

			// ex) 1231234 , k=3
			// ( ) 1231 234 : [0~3] 중 가장 큰 수는 '3'
			// (3) 12 34 : [3~4] 중 가장 큰 수는 '2'
			// (32) 3 4 : [5~5] 중 가장 큰 수는 '3'
			// (323) 4 : [6~6] 중 가장 큰 수는 '4'
			for (int j = idx + 1; j <= number.length() - len; j++) {
				// 가장 큰 수로 '9'가 나온다면 더이상 비교하지 않고 break;
				if (arr[j] == 9) {
					max = arr[j];
					idx = j;
					break;
				}
				// 가장 큰 수 갱신
				if (max < arr[j]) {
					max = arr[j];
					idx = j;
				}
			}
			// 가장 큰 수 붙이기
			answer += max;
			// 만들어야할 자릿수 1씩 감소
			len--;
		}
		return answer;
	}
}