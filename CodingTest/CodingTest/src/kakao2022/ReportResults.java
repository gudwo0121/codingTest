package kakao2022;

import java.util.Arrays;

public class ReportResults {

	// case 1
	static String[] id_list = { "muzi", "frodo", "apeach", "neo" };
	static String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
	static int k = 2;

	public static void main(String[] args) {
		ReportResults reportResults = new ReportResults();
		int[] answer = reportResults.solution1(id_list, report, k);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1
	public int[] solution1(String[] id_list, String[] report, int k) {
		int[] answer = {};
		
		
		
		return answer;
	}
}
