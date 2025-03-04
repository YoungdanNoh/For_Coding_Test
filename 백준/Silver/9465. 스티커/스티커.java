import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(bf.readLine().trim());

            int[][] stickers = new int[2][n+1]; //스티커 입력 받기
            int[][] dp = new int[2][n+1];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(bf.readLine().trim());
                for (int k = 1; k < n+1; k++) {
                    stickers[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // 첫 열은 자기 자신이 최대
            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];

            // 이제 두번째 열부터 진행하면서 dp 배열을 갱신한다.
            for (int k = 2; k < n+1; k++) {
                dp[0][k] = Math.max(dp[1][k-1], dp[1][k-2]) + stickers[0][k];
                //대각선 아래 둘 중 최대값을 더해주기
                dp[1][k] = Math.max(dp[0][k-1], dp[0][k-2]) + stickers[1][k];
                //대각선 위 둘 중 최대값을 더해주기
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
