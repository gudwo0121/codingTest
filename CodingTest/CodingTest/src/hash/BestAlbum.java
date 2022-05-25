package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BestAlbum {

	// case 1
	static String[] genres = { "classic", "pop", "classic", "classic", "pop" };
	static int[] plays = { 500, 600, 150, 800, 2500 };

	public static void main(String[] args) {
		BestAlbum bestAlbum = new BestAlbum();
		int[] answer = bestAlbum.solution1(genres, plays);
		System.out.println(Arrays.toString(answer));
	}

	// solution 1
	// 이중 HashMap, 밸류값 기준 정렬 메소드 활용
	public int[] solution1(String[] genres, int[] plays) {
		// <장르, 곡 정보>
		HashMap<String, Object> genresMap = new HashMap<String, Object>();
		// <장르, 총 장르 재생수>
		HashMap<String, Integer> playMap = new HashMap<String, Integer>();
		ArrayList<Integer> resultAL = new ArrayList<Integer>();

		for (int i = 0; i < genres.length; i++) {
			// 장르 변수
			String key = genres[i];
			// 곡 정보 : <곡 고유번호, 재생횟수>
			HashMap<Integer, Integer> infoMap;

			if (genresMap.containsKey(key)) {
				infoMap = (HashMap<Integer, Integer>) genresMap.get(key);
			} else {
				infoMap = new HashMap<Integer, Integer>();
			}

			infoMap.put(i, plays[i]);
			genresMap.put(key, infoMap);

			// 재생수 누적
			if (playMap.containsKey(key)) {
				playMap.put(key, playMap.get(key) + plays[i]);
			} else {
				playMap.put(key, plays[i]);
			}
		}

		// 여기까지 진행 후 상태 ex
		// playMap : {pop=3100, classic=1450}
		// genresMap : {pop={1=600, 4=2500}, classic={0=500, 2=150, 3=800}}

		int mCnt = 0;
		// playMap의 밸류값에 따라 내림차순 Iterator 선언
		Iterator it = sortByValue(playMap).iterator();

		// Iterator 끝까지 반복
		while (it.hasNext()) {
			String key = (String) it.next();
			Iterator indexIt = sortByValue((HashMap<Integer, Integer>) genresMap.get(key)).iterator();
			// 2곡 제한 변수
			int playsCnt = 0;

			// 내림차순 정렬이 된 Iterator에서 상위 2개 limit 후, 리스트에 삽입
			while (indexIt.hasNext()) {
				resultAL.add((int) indexIt.next());
				mCnt++;
				playsCnt++;
				if (playsCnt > 1)
					break;
			}
		}

		// List to Array
		int[] answer = new int[resultAL.size()];
		for (int i = 0; i < resultAL.size(); i++) {
			answer[i] = resultAL.get(i).intValue();
		}

		return answer;
	}

	// 밸류값 기준 오름차순 정렬 메소드
	private ArrayList sortByValue(final Map map) {
		ArrayList<Object> keyList = new ArrayList();
		keyList.addAll(map.keySet());

		Collections.sort(keyList, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				return ((Comparable) v2).compareTo(v1);
			}
		});

		return keyList;
	}
}