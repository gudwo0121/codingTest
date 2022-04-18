package kakao2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ReportResults {

	// case 1
	static String[] id_list = { "muzi", "frodo", "apeach", "neo" };
	static String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
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

	// solution 1
	public int[] solution1(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		// 이름 : id_list 인덱스 순서
		HashMap<String, Integer> idIndex = new HashMap<>();
		// 피신고자 : 신고자들
		HashMap<String, ArrayList<String>> reportMap = new HashMap<>();

		for (int i = 0; i < id_list.length; i++) {
			// id_list의 이름 순서를 인덱스로 기억 = 순서에 맞춰 출력하기 위함
			idIndex.put(id_list[i], i);
			// new 선언으로 key & list of Value 생성
			reportMap.put(id_list[i], new ArrayList<>());
		}

		// 신고 정보에서
		for (String reported : report) {
			// 공백을 기준으로 split
			// temp[0] = 신고자
			// temp[1] = 피신고자
			String[] temp = reported.split(" ");

			// 중복 신고가 아니라면
			if (!reportMap.get(temp[1]).contains(temp[0])) {
				// reportedMap value 값에 신고자 누적 추가
				reportMap.get(temp[1]).add(temp[0]);
			}
		}

		// 피신고자 반복
		for (String id : reportMap.keySet()) {
			// size = 신고당한 횟수
			// 피신고자가 k번 이상 신고당했다면
			if (k <= reportMap.get(id).size()) {
				// 신고자들 반복
				for (String reporter : reportMap.get(id)) {

					// idIndex = { muzi=0, neo=3, frodo=1, apeach=2 }
					// frodo -> muzi -> muzi -> apeach
					// answer[1]++ -> answer[0]++ -> answer[0]++ -> answer[2]++
					// = [2, 1, 1, 0]

					// 담아둔 idIndex를 기준으로 메일 수신 횟수 1씩 누적 증가
					answer[idIndex.get(reporter)]++;
				}
			}
		}
		return answer;
	}

}