import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int M;
    public static int[] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        perm(0, 1);
    }

    public static void perm(int cnt, int start) {
        if(cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i] + " ");
            }
            System.out.println(sb.toString());
            sb.setLength(0);

            return;
        }

        for (int i = start; i <= N; i++) {
            arr[cnt] = i;
            perm(cnt + 1, i);

        }

    }
}
