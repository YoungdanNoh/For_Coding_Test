import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        
        int row = (int) Math.ceil((double) n / (double) w);
        int[][] box = new int[row][w];
        
        boolean flag = true;
        boolean flag2 = false;
        int tmp = 1;
        for(int i=row-1; i>=0; i--){
            
            if(flag){
                for(int j=0; j<w; j++){
                    box[i][j] = tmp++;
                    
                    if(tmp == (n+1)){
                        flag2 = true;
                        break;
                    }
                }
                flag = false;
                
            }else{
                for(int j=w-1; j>=0; j--){
                    box[i][j] = tmp++;
                    
                    if(tmp == (n+1)){
                        flag2 = true;
                        break;
                    }
                }
                flag = true;
                
            }
            
            if(flag2) break;
            
        }
        
        int result = -1; // num의 열 번호
        for(int i=0; i<row; i++){
            for(int j=0; j<w; j++){
                if(box[i][j] == num){
                    result = j;
                    break;
                }
                
            }
            
            if(result >= 0) break;
        }
        
        if(box[0][result] != 0){
            return row - (int) Math.ceil((double) num / (double) w) + 1;
            
        }else{
            return row - (int) Math.ceil((double) num / (double) w);
            
        }
        
    }
}