import java.util.*;

class Solution {
    public int solution(int[] cards) {
        
        boolean[] visited = new boolean[cards.length];
        ArrayList<Integer> list = new ArrayList<>();
        
        while(true){
            
            int idx = -1; // 현재 검사할 상자 번호
            for(int i=0; i<visited.length; i++){
                if(!visited[i]){
                    idx = i;
                    break;
                }
            }
            
            if(idx == -1){
                // 모든 상자에 대해서 검사를 다 함
                break;
            }
            
            int cnt = 0;
            while(true){
                int num = cards[idx];
                visited[idx] = true;
                
                cnt++;
                
                if(!visited[num-1]){
                    // idx 상자 안에 들어있는 상자 번호의 상자도 방문 가능
                    idx = num-1;
                    
                }else{
                    
                    break;
                }
            }
            
            list.add(cnt);
        }
        
        Collections.sort(list);
        
        if(list.size() == 1) return 0;
        
        return list.get(list.size()-1) * list.get(list.size()-2);
    }
}