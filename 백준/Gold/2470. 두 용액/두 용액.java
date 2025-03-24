import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine().trim());
        st = new StringTokenizer(bf.readLine().trim());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = N-1;
        //양 끝을 가리키는 것으로 시작한다.

        int result = Integer.MAX_VALUE;
        int sol1 = 0, sol2 = 0;

        while (start < end) {
            int sum = arr[start] + arr[end];

            if(Math.abs(sum) <= Math.abs(result)) {
                result = sum;
                sol1 = arr[start];
                sol2 = arr[end];
            }

            if(sum <= 0){
                start++;

            }else{
                end--;
            }
        }

        System.out.println(sol1 + " " + sol2);
    }
}
