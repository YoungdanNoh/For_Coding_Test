import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[10001];
        dp[0] = 1;
        for(int i=1; i<4; i++){
            for(int j=1; j<10001; j++){
                if(j-i >= 0){
                    dp[j] += dp[j-i];
                }
            }
        }

        int T = Integer.parseInt(bf.readLine());
        for(int t=0; t<T; t++) {
            int N = Integer.parseInt(bf.readLine());
            sb.append(dp[N] + "\n");

        }
        System.out.println(sb);
    }
}
