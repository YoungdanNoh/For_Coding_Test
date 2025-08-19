import java.util.*;

class Solution {
    
    // x가 이미 배정된 경우, 다음으로 시도할 방 번호를 가리킴
    Map<Long, Long> next = new HashMap<>();
    
    // x 이상에서의 가장 작은 빈 방을 찾음
    public long find(long x) {
        
        if (!next.containsKey(x)) {
            // x방이 비어있으면 해당 방을 줌
            // x방 요청이 왔을 때 줄 수 있는 방 번호는 x+1번부터 시작
            next.put(x, x + 1);
            return x;
        }
        
        // x방을 배정받을 수 없다면 next[x]로 점프해서 빈 방 찾기
        long empty = find(next.get(x));
        
        // 경로 압축: 다음에 x요청이 오면 그 다음 빈 방부터 탐색
        next.put(x, empty + 1);
        return empty;
    }
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for (int i = 0; i < room_number.length; i++) {
            long r = find(room_number[i]);
            
            answer[i] = r;
        }
        
        return answer;
    }
}