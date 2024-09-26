import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] input;
    static int[] selected;
    static int[][] S;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine().trim());
        input = new int[N];
        selected = new int[N/2];
        S = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
            input[i] = i;
        }

        combi(0, 0);
        System.out.println(ans);
    }

    private static void combi(int start, int cnt) {
        if(cnt == N/2){
            //System.out.println(Arrays.toString(selected));
            int[] non_selec = new int[N/2];
            int idx = 0;
            boolean[] selec = new boolean[N];

            for (int i = 0; i < N/2; i++) {
                selec[selected[i]] = true;
            }
            for (int i = 0; i < N; i++) {
                if(!selec[i]){
                    non_selec[idx++] = i;
                }
            }

            int t1 = 0, t2 = 0;

            for (int i = 0; i < (N/2-1); i++) {
                for (int j = i+1; j < N/2; j++) {
                    //System.out.println(S[selected[i]][selected[j]] + ", " + S[selected[j]][selected[i]]);
                    t1 += (S[selected[i]][selected[j]] + S[selected[j]][selected[i]]);
                    t2 += (S[non_selec[i]][non_selec[j]] + S[non_selec[j]][non_selec[i]]);
                }
            }
            //System.out.println(t1 + ", " + t2);
            ans = Math.min(ans, (Math.abs(t1-t2)));
            return;
        }
        for (int i = start; i < N; i++) {
            selected[cnt] = input[i];
            combi(i+1, cnt+1);
        }
    }
}