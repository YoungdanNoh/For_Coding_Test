import java.util.*;

class Solution {
    static int N;
    static int[][] Dice;
    static boolean[] isSelected;
    static ArrayList<Integer> aScore = new ArrayList<>();
    static ArrayList<Integer> bScore = new ArrayList<>();
    static int MaxWin = 0;
    static boolean[] result;
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        Dice = dice;
        result = new boolean[N];
        
        isSelected = new boolean[N];
        combi(0, 0);
        
        int[] answer = new int[N/2];
        int idx = 0;
        for(int i=0; i<N; i++){
            if(result[i]){
                answer[idx++] = i+1;
            }
        }
        
        return answer;
    }
    
    public void combi(int start, int cnt){
        if(cnt == N/2){
            ArrayList<Integer> aIdx = new ArrayList<>();
            ArrayList<Integer> bIdx = new ArrayList<>();
            
            for(int i=0; i<N; i++){
                if(isSelected[i]) aIdx.add(i);
                else bIdx.add(i);
            }
            
            aScore.clear();
            bScore.clear();
            
            // 현재의 주사위 조합에서 나올 수 있는 수의 합 모두 구하기
            dfs(0, aIdx, 0, true);
            dfs(0, bIdx, 0, false);
            
            Collections.sort(bScore);
            int win = 0;
            for (int aSum : aScore) {
                win += lowerBound(aSum);
                // bScore 중 하나의 aSum에 대해 점수가 낮은 것의 개수
            }
            
            if(MaxWin < win){
                MaxWin = win;
                result = isSelected.clone();
            }
            
            return;
        }
        
        for(int i=start; i<N; i++){
            isSelected[i] = true;
            combi(i+1, cnt+1);
            isSelected[i] = false;
        }
    }
    
    public void dfs(int cnt, ArrayList<Integer> idx, int sum, boolean a){
        // a = true라면 a의 경우의 수를 구하는 것
        
        if(cnt == N/2){
            if(a){
                aScore.add(sum);
                
            }else{
                bScore.add(sum);
                
            }
            
            return;
        }
        
        int diceIdx = idx.get(cnt);
        for(int i=0; i<6; i++){
            dfs(cnt+1, idx, sum + Dice[diceIdx][i], a);
        }
        
    }
    
    public int lowerBound(int x){
        int start = 0;
        int end = bScore.size()-1;
        int result = bScore.size();
        
        while(start <= end){
            int mid = (start + end) / 2;
            
            if(bScore.get(mid) >= x){
                result = mid;
                end = mid - 1;
                
            }else{
                start = mid + 1;
                
            }
            
        }
        
        return result;
    }
}