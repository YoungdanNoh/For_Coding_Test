import java.util.*;
import java.io.*;

public class Main {

    public static int w(int a, int b, int c) {
        if(a <= 0 || b <= 0 || c <= 0){
            return 1;
        }
        if(dp[a][b][c] != 0){
            return dp[a][b][c];
        }
        if(a > 20 || b > 20 || c > 20){
            return dp[a][b][c] = w(20, 20, 20);
        }

        if(a < b && b < c){
            return dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        }else{
            return dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        }
    }

    static int[][][] dp = new int[51][51][51];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1){
                break;
            }

            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + w(a, b, c));

        }
    }
}
