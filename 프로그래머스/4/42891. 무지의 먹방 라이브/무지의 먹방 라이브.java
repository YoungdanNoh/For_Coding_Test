import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        long total = 0;
        for (int t : food_times) total += t;
        if (total <= k) return -1;

        // [time, index]
        List<int[]> foods = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) {
            if (food_times[i] > 0) foods.add(new int[]{food_times[i], i + 1});
        }

        // 시간 기준 오름차순
        foods.sort(Comparator.comparingInt(a -> a[0]));

        long prev = 0;   // 이전 레벨까지 깎인 양
        int i = 0;       // 현재 레벨 시작 위치
        int n = foods.size();

        while (i < n) {
            long cur = foods.get(i)[0];
            long diff = cur - prev;        // 이번 레벨 두께
            
            if (diff != 0) {
                long cnt = n - i;          // 이번 레벨에 남아있는 개수
                long spend = diff * cnt;   // 이번 레벨 전부 깎는 데 드는 시간

                if (k >= spend) {
                    k -= spend;
                    prev = cur;            // 레벨 상승
                    
                } else {
                    // 이번 레벨을 전부 못 깎음 -> 남은 것 중에서 회전하여 선택
                    List<int[]> rest = new ArrayList<>(foods.subList(i, n));
                    rest.sort(Comparator.comparingInt(a -> a[1])); // 인덱스 오름차순
                    int pos = (int) (k % rest.size());
                    
                    return rest.get(pos)[1];
                }
                
            }
            // 현재 시간(cur)과 같은 애들(이번 레벨에서 다 먹은 음식) 스킵
            while (i < n && foods.get(i)[0] == cur) i++;
        }

        return -1; // k시간보다 음식이 적음
    }
}