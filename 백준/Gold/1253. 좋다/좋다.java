import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        num = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);

        if(N == 1) {
            System.out.println(0);

        }else{
            int result = 0;

            for (int i = 0; i < N; i++) {
                int target = num[i];

                int start = 0;
                int end = N - 1;

                while (start < end) {
                    int sum = num[start] + num[end];

                    if(sum == target) {
                        if(start == i){
                            start++;
                            continue;

                        }else if (end == i) {
                            end--;
                            continue;

                        }else{
                            result++;
                            break;
                        }
                    }

                    if(sum < target) {
                        start++;

                    }else {
                        end--;
                    }
                }
            }

            System.out.println(result);
        }
    }

}
