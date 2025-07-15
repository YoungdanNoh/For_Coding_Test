import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        long total = 0;
        long q1_sum = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for(int i=0; i<queue1.length; i++){
            total = total + queue1[i] + queue2[i];
            q1_sum += queue1[i];
            
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        
        if(total % 2 == 1) return -1;
        
        int result = 0;
        long target = total/2;
        
        while(true){
            
            // 더이상 순회해도 안됨
            if(result > (queue1.length + queue2.length)*2) return -1;
            
            if(q1_sum == target) break;
            
            if(q1_sum > target){
                int tmp = q1.poll();
                q1_sum -= tmp;
                
                q2.offer(tmp);
                
            }else{
                int tmp = q2.poll();
                
                q1_sum += tmp;
                q1.offer(tmp);
                
            }
            
            result++;
            
        }
        
        return result;
    }
}