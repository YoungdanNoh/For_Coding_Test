import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        
        int[][] b = new int[rows+1][columns+1];
        int start = 1;
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                b[i][j] = start++;
            }
        }
        
        int[] dx = {0, -1, 0, 1}; //왼쪽꺼, 위쪽꺼, 오른쪽꺼, 아래꺼 가져오기
        int[] dy = {-1, 0, 1, 0};
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<queries.length; i++){
            ArrayList<Integer> tmp = new ArrayList<>();
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            
            tmp.add(b[x1][y2]);
            int ur = b[x1][y2]; // 오른쪽 위
            for(int y=y2; y>=y1; y--){
                if(y==y1){
                    b[x1][y] = b[x1+1][y];
                    tmp.add(b[x1][y]);
                    continue;
                }
                b[x1][y] = b[x1][y-1];
                tmp.add(b[x1][y]);
            }
            
            tmp.add(b[x2][y1]);
            int dl = b[x2][y1]; // 왼쪽 아래
            for(int y=y1; y<=y2; y++){
                if(y==y2){
                    if((x2-x1) == 1){
                        b[x2][y] = ur;
                        
                    }else{
                        b[x2][y] = b[x2-1][y];
                    }
                    tmp.add(b[x2][y]);
                    continue;
                }
                b[x2][y] = b[x2][y+1];
                tmp.add(b[x2][y]);
            }
            
            // 왼쪽 세로
            for(int x=x1+1; x<=x2-1; x++){
                if(x==x2-1){
                    b[x][y1] = dl;
                    continue;
                }
                b[x][y1] = b[x+1][y1];
                tmp.add(b[x][y1]);
            }
            
            // 오른쪽 세로
            for(int x=x2-1; x>=x1+1; x--){
                if(x==x1+1){
                    b[x][y2] = ur;
                    continue;
                }
                b[x][y2] = b[x-1][y2];
                tmp.add(b[x][y2]);
            }
            
            int min = Integer.MAX_VALUE;
            for(int k=0; k<tmp.size(); k++){
                if(min > tmp.get(k)){
                    min = tmp.get(k);
                }
            }
            result.add(min);
            
        }
        
        
        int[] answer = new int[result.size()];
        for(int k=0; k<result.size(); k++){
            answer[k] = result.get(k);
        }
        
        return answer;
    }
}