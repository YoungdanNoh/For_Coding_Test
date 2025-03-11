import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] L, J;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine().trim());
        L = new int[N+1];
        J = new int[N+1];

        st = new StringTokenizer(bf.readLine().trim());
        for (int i = 1; i < N+1; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine().trim());
        for (int i = 1; i < N+1; i++) {
            J[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1][101]; // 사람 수, 체력

        for (int i = 1; i < N+1; i++) {
            // 사람 수
            for (int j = 1; j < 101; j++) {
                //체력
                if(j > L[i]){
                    // 현재 사람에게 인사할 체력이 남아있는가?
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-L[i]]+J[i]);
                    // 이전 사람까지만 인사 vs 현재 사람을 포함하여 인사
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N+1; i++) {
            for(int j = 0; j < 101; j++){
                result = Math.max(result, dp[i][j]);
            }
        }
        System.out.println(result);
    }
}
