import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        
        char[][] b = new char[m][n];
        
        for(int i=0; i<m; i++){
            char[] tmp = board[i].toCharArray();
            
            for(int j=0; j<n; j++){
                b[i][j] = tmp[j];
            }
        }
        
        int result = 0;
        while(true){
            
            Set<String> s = new HashSet<>();
            
            for(int i=0; i<m-1; i++){
                for(int j=1; j<n; j++){
                    char cur = b[i][j]; // 현재 캐릭터
                    
                    if(cur == 'X') continue;
                    
                    if(b[i][j-1] == cur && b[i+1][j-1] == cur && b[i+1][j] == cur){
                        // 2x2가 됨.
                        s.add(i + " " + j);
                        s.add(i + " " + (j-1));
                        s.add((i+1) + " " + (j-1));
                        s.add((i+1) + " " + j);
                        
                    }
                }
            }
            
            if(s.size() == 0){
                break;
            }
            
            result += s.size();
            
            Iterator iter = s.iterator();
            while(iter.hasNext()){
                
                String[] ij = ((String)iter.next()).split(" ");
                
                
                b[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] = 'X';
            }
            
            // 내리기
            for(int j=0; j<n; j++){
                
                int idx = m - 1; // 아래쪽에서부터 채움
                for (int i = m - 1; i >= 0; i--) {
                    if (b[i][j] != 'X') {
                        b[idx][j] = b[i][j];
                        idx--;
                    }
                }
                
                // 남은 위쪽은 X로 채움
                for (int i = idx; i >= 0; i--) {
                    b[i][j] = 'X';
                }
                
            }
            
        }
        
        return result;
    }
}