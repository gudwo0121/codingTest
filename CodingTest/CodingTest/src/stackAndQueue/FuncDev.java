package stackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class FuncDev {

	// case 1
	static int[] progresses = { 93, 30, 55 };
	static int[] speeds = { 1, 30, 5 };

	// case 2
//	static int[] progresses = { 95, 90, 99, 99, 80, 99 };
//	static int[] speeds = { 1, 1, 1, 1, 1, 1 };

	public static void main(String[] args) {
		FuncDev funcDev = new FuncDev();
		int[] answer = funcDev.solution1(progresses, speeds);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1 = 더 느림
	// while 문을 활용하여 남은 작업일을 계산
	// 링크드해시맵 활용 = 순서가 보장되는 map
	public int[] solution1(int[] progresses, int[] speeds) {
		// 순서가 보장되는 map
		Map<Integer, Integer> map = new LinkedHashMap<>();

		int day = -1;
		for (int i = 0; i < progresses.length; i++) {
			// 남은 작업일 계산
			while (progresses[i] + (day * speeds[i]) < 100) {
				day++;
			}
			// 배포 작업 수 누적
			map.put(day, map.getOrDefault(day, 0) + 1);
		}

		// LinkedHashMap to array
		int[] answer = new int[map.size()];
		int j = 0;
		for (int ans : map.values())
			answer[j++] = ans;

		return answer;
	}

	// solution 2 = 더 빠름
	// 자신보다 적게 걸리는 작업들을 포함하여 배포 작업 수 누적 count
	public int[] solution2(int[] progresses, int[] speeds) {
		// 마지막에 list to array를 위함
		ArrayList<Integer> list = new ArrayList<>();

		// 남은 작업 일수들을 계산
		for (int i = 0; i < progresses.length; i++) {
			// 몫
			int quot = (100 - progresses[i]) / speeds[i];
			// 나머지
			int remain = (100 - progresses[i]) % speeds[i];

			// 나머지가 존재하면 초과 근무
			if (remain == 0) {
				progresses[i] = quot;
			}
			// 나머지 없으면 딱 맞게 작업완료
			else {
				progresses[i] = quot + 1;
			}
		}

		// 처음 남은 일수 최대값 하드코딩
		int max = progresses[0];
		// 마지막 누적 배포 수를 담기 위함
		int cycle = 0;
		// 누적 배포 수
		int cnt = 0;
		for (int day : progresses) {
			cycle++;

			// 함께 배포되는 작업 개수 누적
			if (max < day) {
				list.add(cnt);
				cnt = 1;
				max = day;
			} else {
				cnt++;
			}

			// 마지막 배포 수 담기
			if (cycle == progresses.length)
				list.add(cnt);
		}

		// list to array
		int[] answer = new int[list.size()];
		int j = 0;
		for (int ans : list) {
			answer[j++] = ans;
		}

		return answer;
	}
}