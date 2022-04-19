package greedy;

public class JoyStick {

	// case 1
//	static String name = "JEROEN";

	// case 2
	static String name = "JAN";

	// case 3
//	static String name = "JAZN";

	public static void main(String[] args) {
		JoyStick joyStick = new JoyStick();
		int answer = joyStick.solution1(name);
		System.out.println(answer);
	}

	// solution 1 (상당히 어려움, 복습필요)
	public int solution1(String name) {
		int answer = 0;
		int length = name.length();
		// 오른쪽으로 진행하는 경우 하드 코딩
		int move = length - 1;

		for (int i = 0; i < name.length(); i++) {
			// 상하 이동 횟수
			// 상하는 고정이라 미리 계산
			answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

			// 현재 위치 바로 앞에
			int index = i + 1;
			// 커서가 이동할 공간이 있고, 그 알파벳이 'A'이 아닐때까지 반복하며
			while (index < length && name.charAt(index) == 'A') {
				// 오른쪽 진행) 다음 번 A가 아닌 문자의 인덱스 값
				// ex) BAAAB = 처음 인덱스는 0, 다음 번 인덱스는 4
				index++;
			}

			// 좌우 이동 횟수
			// name의 각 인덱스에서 출발 기준으로 왼쪽 or 오른쪽 진행시 가장 적은 이동 횟수 min으로 갱신

			// 1. Math.min(i, length - index)
			// -> Math.min(현재 커서까지 이동 횟수, 다음번 A가 아닌 문자까지 거꾸로 이동 횟수)
			// -> ex) DEAACBF : Math.min(처음부터 E까지, 끝에서 C까지)

			// 2. (i + length - index)
			// -> (현재 커서까지 이동 횟수 + 다음번 A가 아닌 문자까지 거꾸로 이동 횟수)
			// -> (현재 커서 위치에서 거꾸로 끝까지 총 이동 횟수)
			// -> ex) DEAACBF : E -> D -> F -> B -> C 까지 이동 횟수

			// 3. (1)에서 i 선택 시 : (i + length - index + i)
			// ex) DEAACBF : D -> E -> (전환) -> D -> F -> B -> C
			// 정상 진행 끝 찍고 역순 진행

			// 3. (1)에서 (length - index) 선택 시 : (i + length - index + length - index)
			// ex) DEAACBF : D -> F -> B -> C -> (전환) -> B -> F -> D -> E
			// 역순 진행 끝 찍고 다시 정상 진행\

			// 4. 문자열을 모두 방문한 경우의 수 중 가장 적은 이동 횟수로 갱신
			move = Math.min(move, (i + length - index) + Math.min(i, length - index));

		}
		// 상하 + 좌우 이동 횟수
		return answer + move;
	}
}
