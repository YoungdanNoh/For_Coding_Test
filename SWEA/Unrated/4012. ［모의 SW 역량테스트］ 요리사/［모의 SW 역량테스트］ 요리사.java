import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int pick;
    static int[] result;
    static int all;
    static int synergy1;
    static int synergy2;
    static int[][] food;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++) {
            ans = 8*(40000);

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            food = new int[N][N];

            all = 0;
            synergy1 = 0;
            synergy2 = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++) {
                    food[i][j] = Integer.parseInt(st.nextToken());
                    all += food[i][j];
                }
            }

            pick = N/2; //한 요리에 들어가는 재료의 수
            result = new int[pick];

            combi(0, 0);
            System.out.println("#" + t + " " + ans);
        }
    }

    private static void combi(int cnt, int start) {
        if(cnt == pick){
            int[] result2 = new int[pick];
            int idx = 0;
            for(int i=0; i<N; i++){
                int finalI = i;
                if(!Arrays.stream(result).anyMatch(value -> value == (finalI +1))){
                    result2[idx++] = i+1;
                }
            }
            for(int i=0; i<pick; i++){
                for(int j=i+1; j<pick; j++){
                    synergy1 += food[result[i]-1][result[j]-1];
                    synergy1 += food[result[j]-1][result[i]-1];

                    synergy2 += food[result2[i]-1][result2[j]-1];
                    synergy2 += food[result2[j]-1][result2[i]-1];
                }
            }
            ans = Math.min(ans, Math.abs(synergy1-synergy2));
            synergy1 = 0;
            synergy2 = 0;
            return;
        }

        for(int i=start; i<N; i++){
            result[cnt] = i+1;
            combi(cnt+1, i+1);
        }
    }
}