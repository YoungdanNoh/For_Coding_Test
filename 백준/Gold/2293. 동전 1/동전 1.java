import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n+1];
        for (int i = 1; i <= n; i++) {
            coin[i] = Integer.parseInt(bf.readLine().trim());
        }

        int[][] dp = new int[n+1][k+1];
        // n: ~번 동전까지 판단했을 때  k: 몇 원?
        // dp[][] = 경우의 수

        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if(coin[i] > j){
                    //현재 넣으려고 하는 "원"보다 큰 가치라면 pass
                    //이전 동전까지만 사용하는 경우의 수를 넣는다.
                    dp[i][j] = dp[i-1][j];
                }else{
                    //이전 동전까지만 사용하는 경우 vs 내 동전까지 사용하는 경우
                    dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]];
                    //System.out.println("Case #" + j + ": " + (j-coin[i]));
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
