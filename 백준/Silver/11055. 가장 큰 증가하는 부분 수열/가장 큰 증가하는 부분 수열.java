import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine().trim());
        int[] arr = new int[N];

        st = new StringTokenizer(bf.readLine().trim());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = arr[0];

        for (int i = 1; i < N; i++) {
            dp[i] = arr[i]; //현재 위치의 수열 값
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    // 나보다 작은 애가 있다면 비교
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                    // 현재 저장된 값 vs (나보다 작은 값 + 현재 위치의 값)
                }
            }
        }

        int result = dp[0];
        for (int i = 1; i < N; i++) {
            if(result < dp[i]) {
                result = dp[i];
            }
        }
        System.out.println(result);
    }
}