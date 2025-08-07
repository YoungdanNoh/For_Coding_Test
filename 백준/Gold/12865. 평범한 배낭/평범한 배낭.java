import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken()); // 무게
            int v = Integer.parseInt(st.nextToken()); // 가치

            // i: 고려한 물건의 수, j: 배낭의 무게
            for(int j=1; j<K+1; j++) {
                if(j < w){
                    // 현재 물건을 배낭에 넣을 수 없다면
                    dp[i][j] = dp[i-1][j];
                    // 해당 배낭 무게에서 이전 물건까지 고려했을 때의 최대값을 넣는다.

                }else{
                    // 현재 물건을 배낭에 넣을 수 있다면
                    dp[i][j] = Math.max(dp[i-1][j], v+dp[i-1][j-w]);
                    // 현재 물건을 넣지 않거나 넣거나 둘 중의 최대값을 넣는다.
                }

            }
        }

        System.out.println(dp[N][K]);
    }
}
