import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine().trim());
        int K = Integer.parseInt(st.nextToken()); //이미 가지고 있는 랜선의 개수
        int N = Integer.parseInt(st.nextToken()); //필요한 랜선의 개수

        int[] arr = new int[K];

        long start = 1;
        long end = 1;
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(bf.readLine().trim());
            if(arr[i] > end) {
                end = arr[i];
            }
        }

        long result = 0;
        while(start <= end) {
            long mid = (start + end) / 2;

            int cnt = 0; //만들 수 있는 랜선의 개수

            for (int i = 0; i < K; i++) {
                cnt += arr[i]/mid;
            }

            if(cnt >= N) {
                start = mid + 1;
                result = Math.max(result, mid);
            }else{
                end = mid - 1;
            }
        }

        System.out.println(result);

    }
}