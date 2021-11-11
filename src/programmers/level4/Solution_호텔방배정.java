package programmers.level4;

import java.util.*;

/**
 * @author comkkyu
 * @since 21. 11. 11
 *
 * 프로그래머스 Lv.4 호텔방배정 - https://programmers.co.kr/learn/courses/30/lessons/64063
 *
 * 방번호의 범위가 1조라서 배열로 하나하나 체크하면서 풀 수 있는 문제는 아니었다.
 * HashMap 은 Hashing 을 사용하기 때문에 많은 양의 데이터를 검색하는데 뛰어난 성능을 보인다.
 * 따라서 해당 문제를 HashMap 을 이용해서 구현해 보니 시간초과 업싱 통과할 수 있었다.
 *
 * cf. Hashing 이란 키(Key) 값을 해시 함수(Hash Function)라는 수식에 대입시켜 계산한 후 나온 결과를 주소로 사용하여 바로 값(Value)에 접근하게 할 수 하는 방법
 */
public class Solution_호텔방배정 {

    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        int len = room_number.length;
        answer = new long[len];
        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            answer[i] = find(map, room_number[i]);
        }

        return answer;
    }

    public long find(Map<Long, Long> map, long num) { // 방번호 num 이 이미 배정받은 번호인지 탐색해서 적절한 방번호가 반환되는 메서드
        if (!map.containsKey(num)) { // 해당 방번호에 대한 다음 방번호에 대한 정보가 map 에 없다면 그 방번호 그대로 사용 가능
            long next = num + 1; // 다음 방번호를 현재 배정할 방번호보다 1 큰 값으로 
            map.put(num, next); // map 에 넣어주고
            return num; // answer 에는 배정을 원한 방번호를 return
        }

        // 만약에 이미 map 에 들어있는 방번호라면 (이미 배정된 방 번호)
        long empty = find(map, map.get(num)); // 재귀를 통해서 사용할 수 있는 비어있는 방번호를 탐색
        map.put(num, empty+1); // 해당 방번호도 num 이라는 방번호를 요청했을 때 다음 방번호로 map 에 저장.
        return empty; // answer 에는 비어있는 방번호인 empty 를 반환
    }
}
