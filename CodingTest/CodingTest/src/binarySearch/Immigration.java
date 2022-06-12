package binarySearch;

import java.util.Arrays;

public class Immigration {

	// case 1
	static int n = 6;
	static int[] times = { 7, 10 };

	public static void main(String[] args) {
		Immigration immigration = new Immigration();
		long answer = immigration.solution1(n, times);
		System.out.println(answer);
	}

	// solution 1
	// 최대값 = 사람 수 * 가장 오래 걸리는 심사 시간
	// (1 ~ 최대값)까지 이분탐색
	// 심사할 수 있는 사람 수 = (시간 / 각 심사 시간 arr) 누적 합
	// 주의할 점 : I/O의 범위가 int 형의 범위를 벗어나는 경우 오버플로우가 발생
	// 모든 변수를 long 타입으로 변환해서 활용해야함 (입력 값인 n도 포함)
	public long solution1(int n, int[] times) {
		// int to long
		long nLong = (long) n;
		long answer = 0;
		// 심사시간 정렬 = 최대값을 구하기 위함
		Arrays.sort(times);
		// 종료 = 걸리는 시간 최대값
		long high = times[times.length - 1] * nLong;
		// 시작 = 1
		long low = 1;

		// 시작이 종료를 만나면 종료
		while (low <= high) {
			// 중간 = (시작 + 종료) / 2
			long mid = (low + high) / 2;
			// 사람수는 누적합이므로 매번 초기화
			long person = 0;
			for (int time : times)
				// 해당 시간에서 심사가능한 사람 수
				person += mid / time;

			// 심사예정 사람 수와 일치하되 가장 최소로 걸리는 시간대를 찾기 위함
			if (person >= nLong) {
				if (answer == 0) {
					answer = mid;
				} else {
					answer = Math.min(answer, mid);
				}
				// 심사예정 사람 수보다 많으면 종료를 현재 (mid-1)값으로 정의
				high = mid - 1;
			} else {
				// 심사예정 사람 수보다 적으면 시작을 현재 (mid+1)값으로 정의
				low = mid + 1;
			}
		}
		return answer;
	}
}
