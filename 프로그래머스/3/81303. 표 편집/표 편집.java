import java.util.*;

class Solution {
    
    public String solution(int n, int k, String[] cmd) {
        
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        
        for(int i=0; i<n; i++){
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n-1] = -1; // 가장 끝 원소는 가리키는 원소가 없음
        
        Stack<Integer> delRow = new Stack<>();
        
        for(int i=0; i<cmd.length; i++){
            String[] c = cmd[i].split(" ");
            
            if(c[0].equals("U")){
                // 윗 행 선택
                int tmp = Integer.parseInt(c[1]);
                for(int j=0; j<tmp; j++){
                    k = prev[k];
                }
                
            }else if(c[0].equals("D")){
                // 아래 행 선택
                int tmp = Integer.parseInt(c[1]);
                for(int j=0; j<tmp; j++){
                    k = next[k];
                }
                
            }else if(c[0].equals("C")){
                // 현재 선택된 행 삭제 -> 바로 아래 행 선택
                // 단 현재 삭제된 행이 가장 마지막 행인 경우 바로 윗 행 선택
                delRow.push(k); // 행번호
                deleted[k] = true;
                
                int curPrev = prev[k];
                int curNext = next[k];
                
                // curPrev가 -1이라는 것은 내 앞에 노드가 없다는 의미
                // curNext가 -1이라는 것은 내 뒤에 노드가 없다는 의미
                if (curPrev != -1) next[curPrev] = curNext; // 내 앞에있던 애의 next를 내가 가르키던 next로
                if (curNext != -1) prev[curNext] = curPrev; // 내 뒤에 있는 애의 prev를 내가 가르키던 prev로
                
                if(curNext == -1){
                    // 다음 행이 없다면
                    k = curPrev; // 바로 내 앞에 있던 애를 선택
                }else{
                    // 다음 행을 선택
                    k = curNext;
                }
                
            }else{
                // 'Z' : 가장 최근에 삭제된 행을 원래대로 복구
                int restore = delRow.pop(); // 가장 최근에 삭제된 row
                deleted[restore] = false;
                
                int curPrev = prev[restore];
                int curNext = next[restore];
                
                if(curPrev != -1){
                    next[curPrev] = restore; // 내 앞 노드의 next를 나로 바꾸기
                }
                
                if(curNext != -1){
                    prev[curNext] = restore; // 내 뒤 노드의 prev를 나로 바꾸기
                }
                
            }
            
        }
        
        
        char[] ans = new char[n];
        Arrays.fill(ans, 'X');
        
        for(int i=0; i<deleted.length; i++){
            if(!deleted[i]){
                ans[i] = 'O';
            }
        }
        
        return new String(ans);
    }
}