package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class JoyStick {

	// case 1
//	static String name = "JEROEN";

	// case 2
//	static String name = "JAN";

	// case 3
	static String name = "JAZN";

	public static void main(String[] args) {
		JoyStick joyStick = new JoyStick();
		int answer = joyStick.solution1(name);
		System.out.println(answer);
	}

	// solution 1
	// 뒤로가냐 앞으로가냐 = 첫번째 조건
	// 낭비되는 동선인가 판단 = 두번째 조건 = 사이에 A 개수?
	// (왔던길 + 끝부터 A아닌것까지 길이) & ()
	// 전자가 크면 앞으로 ㄱ, 후자가 크면 뒤로 ㄱ (이건 경우에 따라 다를듯?)
	public int solution1(String name) {
		int ud = 0;
		int lr = 0;
		int cursor = 0;
		int r = 0;
		int l = 0;
		int[] arr = new int[name.length()];
		ArrayList<String> list = new ArrayList<>();

		for (String spelling : name.split(""))
			list.add(spelling);

		System.out.println(list);

		int i = 0;
		for (String spelling : list) {
			if (spelling.equals("A")) {
				arr[i++] = 0;
			} else {
				arr[i++] = (int) spelling.charAt(0) - 65;
			}
		}

		System.out.println(Arrays.toString(arr));
		System.out.println(IntStream.of(cursor).sum());

		while (IntStream.of(cursor).sum() != 0) {
			l = 0;
			r = 0;

			// 우측방향 예상 이동거리
			for (int j = cursor; j < name.length(); j++) {
				if (arr[j] != 0) {
					r = j - cursor;
					System.out.println("우측 : " + r);
					break;
				}
			}

			// 좌측방향 예상 이동거리 = 지금까지 이동거리 + 끝부터 첫 문자 만나기까지 거리
			for (int j = name.length(); j >= 0; j--) {
				
			}

		}

		return (lr + ud);
	}
}
