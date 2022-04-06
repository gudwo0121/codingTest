package fullSearch;

import java.util.HashSet;

public class PrimeNumber {

	// case 1
//	static String numbers = "17";

	// case 2
	static String numbers = "011";

	public static void main(String[] args) {
		PrimeNumber primeNumber = new PrimeNumber();
		int answer = primeNumber.solution1(numbers);
		System.out.println(answer);
	}

	// 순열과 조합 : 백트래킹 혹은 재귀함수로 풀이
	// ex) {1,2}
	// 순열(combination) = 자릿수에 맞게 배치하는 모든 경우의 수 = (1,2) (2,1)
	// 조합(premutation) = 중복없는 경우의 수 = (1,2)

	// solution 1
	public int solution1(String numbers) {
		int answer = 0;
		// 중복이 없는 순열을 받을 HashSet
		HashSet<Integer> set = new HashSet<>();

		// 순열 메소드 호출 (순열, 초기 문자, 순열을 담을 공간)
		// ex) {17}
		// 1 - 7 - []
		// 17 - - [1]
		// go back
		// 7 - 1 - [1, 17]
		// 71 - - [1, 17, 7]
		permutation("", numbers, set);

		// 소수 개수(=정답) 구하기
		for (int pmtNum : set) {
			// 숫자가 2라면 (2는 예외적으로 2의 배수지만 소수이다)
			if (pmtNum == 2) {
				// 소수 개수 누적
				answer++;
			}
			// 숫자가 소수판별을 받았고 2의 배수가 아니라면
			if (pmtNum % 2 != 0 && isPrime(pmtNum)) {
				// 소수 개수 누적
				answer++;
			}
		}
		return answer;
	}

	// 순열 메소드 (재귀)
	public void permutation(String strNum, String numbers, HashSet<Integer> set) {
		// 입력받은 숫자의 길이
		int len = numbers.length();

		// 한자리라도 들어있으면 set에 담기
		// 한자리 -> 두자리 -> ... -> n자리 = 순열이라서 전부 담아야한다
		if (!strNum.equals("")) {
			// String to int
//			System.out.println(strNum);
			set.add(Integer.valueOf(strNum));
		}

		// 숫자의 첫자리부터 끝자리까지 순열 조합
		for (int i = 0; i < len; i++) {
			// 파라미터 1 : 순열로 조합된 숫자 (한자리~n자리 반복 조합)
			// 파라미터 2 : 
			// 파라미터 3 : 중복없이 순열로 조합된 숫자들 set
			System.out.println(numbers.substring(0, i) + "+" + numbers.substring(i + 1, len));
			permutation(strNum + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i + 1, len), set);
		}
	}

	// 소수 판별 메소드
	// 소수 판별 메소드
	public boolean isPrime(int num) {
		// 0, 1 이면
		if (num <= 1) {
			// 소수가 아니다
			return false;
		}
		// 홀수의 배수는 3,5,7,9 모두 다르다
		// 짝수의 배수는 2,4,6,8 모두 2의 배수(중복)이기 때문에
		// 나중에 main에서 2로 나누어 떨어지는지만 확인하면 된다
		for (int i = 3; i <= (int) Math.sqrt(num); i += 2) {
			// 숫자가 홀수로 나누어 떨어지는 경우가 한번이라도 있다면
			if (num % i == 0) {
				// 소수가 아니다
				return false;
			}
		}
		// else는 소수다
		return true;
	}
}
