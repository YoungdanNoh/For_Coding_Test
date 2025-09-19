import java.util.*;

class Solution {
    
    static int[] S;
    static ArrayList<Integer>[] graph;
    static int N;
    static int[][] dp;
    
    public int solution(int[] sales, int[][] links) {
        // 1번은 항상 CEO
        S = sales;
        N = sales.length;
        dp = new int[N+1][2];
        for(int i=0; i<N+1; i++){
            for(int j=0; j<2; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<links.length; i++){
            int a = links[i][0]; // 팀장
            int b = links[i][1]; // 팀원
            graph[a].add(b);
        }
        
        dfs(1); // 직원 번호
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void dfs(int id){
        
        if(graph[id].size() == 0){
            dp[id][0] = 0; // 현재 직원이 참석
            dp[id][1] = S[id - 1]; // 현재 직원이 불참
            return;
        }
        
        // 모든 자식 노드들의 참석/불참 중 더 싼 쪽을 택했을 때의 총합
        int sumMin = 0;
        // 자식 중 이미 "참석"이 최적이 되는 애가 있으면 true -> 팀 단위 참석 규칙 충족
        boolean childChosen = false;
        // 모든 자식이 "불참"을 선택하는 게 싸다면, 한 명을 강제로 참석시키기 위한 최소 추가 비용
        int extra = Integer.MAX_VALUE; 
        
        for (int v : graph[id]) {
            dfs(v);

            int minChild = Math.min(dp[v][0], dp[v][1]);
            sumMin += minChild;

            if (dp[v][1] <= dp[v][0]) {
                // 이 자식은 어차피 참석이 더 싸거나 동일 -> 팀 단위 참석 규칙 충족
                childChosen = true;
                
            } else {
                // 불참이 더 싸지만, 규칙 때문에 필요하면 참석으로 바꿀 후보
                extra = Math.min(extra, dp[v][1] - dp[v][0]);
                
            }
            
        }
        
        // id가 참석하는 경우: 자식은 각자 더 싼 쪽 선택
        dp[id][1] = S[id - 1] + sumMin;

        // id가 불참하는 경우: 자식 중 최소 1명은 참석해야 함
        dp[id][0] = sumMin + (childChosen ? 0 : extra);
        
    }
}