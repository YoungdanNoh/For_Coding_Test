//모르겠어서 찾아봄..

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[N];
        if(N==1){
            System.out.println(1);
        }else if(N==2){
            System.out.println(2);
        }else{
            dp[0] = 1;
            dp[1] = 2;
            for(int i=2; i<N; i++){
                dp[i] = (dp[i-1] + dp[i-2])%10007;
            }
            System.out.println(dp[N-1]);
        }
    }
}