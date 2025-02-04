import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine().trim());
        int[] sang = new int[N];
        st = new StringTokenizer(bf.readLine().trim());
        for (int i = 0; i < N; i++) {
            sang[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sang); //정렬

        int M = Integer.parseInt(bf.readLine().trim());
        int[] card = new int[M];
        st = new StringTokenizer(bf.readLine().trim());
        for (int i = 0; i < M; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            int start = 0;
            int end = N;

            while (start <= end) {
                int mid = (start + end) / 2;

                if(mid < 0 || mid >= N) {
                    break;
                }

                if(sang[mid] == card[i]) {
                    result[i] = 1; //해당 카드가 존재함
                    break;
                }

                if(sang[mid] < card[i]) {
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
