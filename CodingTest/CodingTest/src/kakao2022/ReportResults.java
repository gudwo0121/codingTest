package kakao2022;

import java.util.Arrays;
import java.util.HashMap;

public class ReportResults {

	// case 1
	static String[] id_list = { "muzi", "frodo", "apeach", "neo" };
	static String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi", "neo muzi" };
	static int k = 2;

	// case 2
//	static String[] id_list = { "con", "ryan" };
//	static String[] report = { "ryan con", "ryan con", "ryan con", "ryan con" };
//	static int k = 3;

	public static void main(String[] args) {
		ReportResults reportResults = new ReportResults();
		int[] answer = reportResults.solution1(id_list, report, k);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1 not solved
	// 해시셋으로 중복을 제거하라 + 이중 반복문말고 하나씩 나눠서 정리하라
	public int[] solution1(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		HashMap<String, Integer> reportMap = new HashMap<>();
		HashMap<String, Integer> mailMap = new HashMap<>();

		Arrays.sort(report);

		String compare = "";
		for (String id : report) {
			if (compare.equals(id)) {
				break;
			}
			String reportedId = id.substring(id.lastIndexOf(" ") + 1);
			reportMap.put(reportedId, reportMap.getOrDefault(reportedId, 0) + 1);
			compare = id;
		}

		for (String id : reportMap.keySet()) {
			if (reportMap.get(id) >= k) {
				for (String user : report) {
					String reportId = user.substring(0, user.indexOf(" "));
					String reportedId = user.substring(user.lastIndexOf(" ") + 1);

					if (reportedId.equals(id)) {
						mailMap.put(reportId, mailMap.getOrDefault(reportId, 0) + 1);
					}
				}
			}
		}

		int i = 0;
		for (String id : id_list) {
			if (mailMap.get(id) == null) {
				answer[i++] = 0;
			} else {
				answer[i++] = mailMap.get(id);
			}
		}
		
		System.out.println(reportMap);
		System.out.println(mailMap);

		return answer;
	}
}
