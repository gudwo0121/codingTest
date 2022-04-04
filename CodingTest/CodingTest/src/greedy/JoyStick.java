package greedy;

public class JoyStick {

	// case 1
	static String name = "JEROEN";

	// case 2
//	static String name = "JAN";

	// case 3
//	static String name = "JAZN";

	public static void main(String[] args) {
		JoyStick joyStick = new JoyStick();
		int answer = joyStick.solution1(name);
		System.out.println(answer);
	}

	// solution 1
	public int solution1(String name) {
		int answer = 0;
		int length = name.length();

		int index;
		int move = length - 1;

		for (int i = 0; i < name.length(); i++) {
			// 상하 이동 횟수
			answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

			// 현재 위치 바로 앞에
			index = i + 1;
			// 커서가 이동할 공간이 있고, 그 알파벳이 'A'이 아닐때까지 반복하며
			while (index < length && name.charAt(index) == 'A') {
				// 연속되는 'A'의 개수를 누적
				index++;
			}

			// move : 앞으로 쭉가는 경우
			// 현위치 * 2 + ('A'가 아닌 알파벳 개수) : 뒤로 돌아가는 경우
			move = Math.min(move, i * 2 + length - index);
			move = Math.min(move, (length - index) * 2 + i);
		}
		return answer + move;
	}
}
