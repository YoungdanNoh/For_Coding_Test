class Solution {
    
    public void dfs(int n){
        if(n == N){
            // n개 퀸 배치 완료
            answer++;
            return;
        }
        
        for(int j=0; j<N; j++){
            if(v1[j] == 0 && v2[n+j] == 0 && v3[j-n+(N-1)] == 0){
                v1[j] = 1;
                v2[n+j] = 1;
                v3[j-n+(N-1)] = 1;
                
                dfs(n+1);
                
                v1[j] = 0;
                v2[n+j] = 0;
                v3[j-n+(N-1)] = 0;
            }
        }
    }
    
    static int answer = 0;
    static int N;
    static int[] v1; // 가로 or 세로로 배치된 퀸 체크
    static int[] v2; // 오른쪽 위 배치된 퀸 체크
    static int[] v3; // 왼쪽 위 배치된 퀸 체크
    
    public int solution(int n) {
        N = n;
        v1 = new int[n];
        v2 = new int[n*2-1];
        v3 = new int[n*2-1];
        
        dfs(0);
        
        return answer;
    }
}