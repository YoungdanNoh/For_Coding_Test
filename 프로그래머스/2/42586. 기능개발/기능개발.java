import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<progresses.length; i++){
            int pro = 100 - progresses[i];
            int day = (int) Math.ceil((double) pro / speeds[i]);
            
            q.offer(day);
        }
        
        int[] temp = new int[progresses.length];
        int idx = 0;
        while(!q.isEmpty()){
            int cnt = 1;
            int cur = q.poll();
            
            while(!q.isEmpty()){
                if(q.peek() <= cur){
                    cnt += 1;
                    q.poll();
                    
                }else{
                    break;
                }
            }
            temp[idx++] = cnt;
        }
        
        int[] result = new int[idx];
        
        for(int i=0; i<result.length; i++){
            result[i] = temp[i];
        }
        
        return result;
    }
}