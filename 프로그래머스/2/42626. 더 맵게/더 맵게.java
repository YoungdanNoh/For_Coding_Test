import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<scoville.length; i++){
            pq.offer(scoville[i]);
        }
        
        if(pq.peek() >= K){
            return 0;
        }
        
        int cnt = 0;
        
        while (pq.size() > 1){
            cnt++;
            
            int f1 = pq.poll();
            int f2 = pq.poll();
            int newf = f1 + (f2*2);
            
            pq.offer(newf);
            
            if(pq.peek() >= K){
                return cnt;
            }
        }
        
        return -1;
    }
}