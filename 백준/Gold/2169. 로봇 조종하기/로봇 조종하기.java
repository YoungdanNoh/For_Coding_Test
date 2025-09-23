import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];

        // 첫 행은 왼쪽에서만 올 수 있음
        dp[0][0] = board[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + board[0][i];
        }

        // 이후 각 행의 첫행 or 끝행은 위에서 내려오는 값
        for (int i = 1; i < N; i++) {
            int[] left = new int[M];
            int[] right = new int[M];

            // 좌 -> 우
            left[0] = dp[i-1][0] + board[i][0];
            for (int j = 1; j < M; j++) {
                // 위쪽 -> 아래 or 왼쪽 -> 오른쪽으로 오는 것 중 젤 큰 값
                left[j] = Math.max(left[j-1], dp[i-1][j]) + board[i][j];
            }

            // 우 -> 좌
            right[M-1] = dp[i-1][M-1] + board[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                // 위쪽 -> 아래 or 오른쪽 -> 왼쪽으로 오는 것 중 젤 큰 값
                right[j] = Math.max(right[j+1], dp[i-1][j]) + board[i][j];
            }

            // 둘 중 최댓 값 선택
            for(int j=0; j<M; j++){
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}
