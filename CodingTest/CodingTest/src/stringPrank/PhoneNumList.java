package stringPrank;

import java.util.Arrays;

public class PhoneNumList {

	// case 1
	static String[] phone_book = { "119", "97674223", "1195524421" };

	// case 2
//	static String[] phone_book = { "123", "456", "789" };

	// case 3
//	static String[] phone_book = { "12", "123", "1235", "567", "88" };

	public static void main(String[] args) {
		PhoneNumList phoneNumList = new PhoneNumList();
		boolean answer = phoneNumList.solution1(phone_book);
		System.out.println(answer);
	}
	
	// ( 문제 꿀팁 )
	// case 처럼 숫자가 문자열 배열에 담겨있고
	// 그 배열을 sort() 한다면 숫자의 크기가 아닌 사전순으로 오름차 정렬이 된다
	// 때문에 가장 인접한 뒷번호와 순차 비교를 1회만 반복해줘도 접두사가 존재하는지 알 수 있다

	// solution 1 = startsWith 사용
	public boolean solution1(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		for (int i = 1; i < phone_book.length; i++) {
			// 뒷번호가 앞번호로 시작한다면
			if (phone_book[i].startsWith(phone_book[i - 1])) {
				answer = false;
			}
		}
		return answer;
	}

	// solution 2 = 정규표현식
	// 1번보다 느림
	public boolean solution2(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		for (int i = 1; i < phone_book.length; i++) {
			// 문자.* : 처음 (문자)로 시작하는 모든 문자열을 포함
			// "뒷번호"가 "앞번호+@"라면
			if (phone_book[i].matches(phone_book[i - 1] + ".*")) {
				answer = false;
			}
		}
		return answer;
	}
}