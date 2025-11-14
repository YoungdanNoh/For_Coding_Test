import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][N];

        // 시작지점이 딸기 우유라면 마시고 시작
        if(arr[0][0] == 0){
            dp[0][0] = 1;

        }else{
            dp[0][0] = 0;

        }

        // 1번째 행은 왼쪽 -> 오른쪽으로만 올 수 있음
        for (int j = 1; j < N; j++) {
            int cnt = dp[0][j-1];
            if(arr[0][j] == cnt%3){
                cnt++;
            }
            dp[0][j] = cnt;
        }

        // 1번째 열은 위 -> 아래로만 올 수 있음
        for (int i = 1; i < N; i++) {
            int cnt = dp[i-1][0];
            if(arr[i][0] == cnt%3){
                cnt++;
            }
            dp[i][0] = cnt;
        }

        // 나머지
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                // 위 or 왼쪽 중 더 많이 우유를 마신 쪽으로 선택
                int cnt = Math.max(dp[i-1][j], dp[i][j-1]);
                if(arr[i][j] == cnt%3){
                    cnt++;
                }
                dp[i][j] = cnt;
            }
        }

        System.out.println(dp[N-1][N-1]);

    }
}
