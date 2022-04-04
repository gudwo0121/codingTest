package greedy;

public class GymSuit {

	// case 1
	static int n = 5;
	static int[] lost = { 2, 4 };
	static int[] reserve = { 1, 3, 5 };

	// case 2
//	static int n = 5;
//	static int[] lost = { 2, 4 };
//	static int[] reserve = { 3 };

	// case 3
//	static int n = 3;
//	static int[] lost = { 3 };
//	static int[] reserve = { 1 };

	// case 4
//	static int n = 5;
//	static int[] lost = { 1, 2, 4 };
//	static int[] reserve = { 2, 4, 5 };

	public static void main(String[] args) {
		GymSuit gymSuit = new GymSuit();
		int answer = gymSuit.solution1(n, lost, reserve);
		System.out.println(answer);
	}

	// solution 1
	// 배열을 만들어 학생 체육복 현황을 0 기준으로 없음(-1), 있음(0), 많음(+1)로 표현
	// 배열에서 (앞뒤 번호 학생 존재 && 그 학생이 여벌이 있음) = 나(+1), 걔(-1) 끝까지 1회 반복
	public int solution1(int n, int[] lost, int[] reserve) {

		// 총 학생 수로 시작
		int answer = n;
		int[] student = new int[n];

		// student 배열에 lost는 -1, reserve는 +1
		// 두벌 있다가 한벌 잃어버린 학생 + 한벌도 없는 학생 + 두벌 있는 학생 모두 표시
		// 학생들의 체육복 현황을 표로 합친다는 느낌?
		for (int i : lost)
			student[i - 1]--;
		for (int i : reserve)
			student[i - 1]++;

		for (int i = 0; i < student.length; i++) {

			// 한벌도 없는 학생 중에
			if (student[i] == -1) {

				// 바로 앞 번호 학생이 존재하고
				// 그 학생이 두벌 있는 학생이면
				if (i - 1 >= 0 && student[i - 1] == 1) {
					// 나는 한벌 빌리고
					student[i]++;
					// 그 학생은 한벌 빌려준다
					student[i - 1]--;
				}

				// 바로 뒷 번호 학생이 존재하고
				// 그 학생이 두벌 있는 학생이면
				else if (i + 1 < student.length && student[i + 1] == 1) {
					// 나는 한벌 빌리고
					student[i]++;
					// 그 학생은 한벌 빌려준다
					student[i + 1]--;
				}

				// 앞뒤 번호 학생이 없거나
				// 있다고 해도 그 학생이 두벌 있는 학생이 아니라면
				// 별짓을 한다해도 못빌림 = 총 학생 수에서 감소(-1)
				else
					answer--;
			}
		}
		return answer;
	}
}