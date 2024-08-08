import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] tree = new int[N];
        st = new StringTokenizer(bf.readLine());
        int start = 0;
        int end = 0;
        int result = 0;
        int mid = 0;

        for(int i=0; i<N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
            end = Math.max(tree[i], end);
        }

        while (start <= end){
            //M미터 이상 자를 수 있을 때까지 반복
            mid = (start + end) / 2; //이 mid가 절단기의 높이가 됨

            long temp = 0;

            for(int i=0; i<N; i++){
                if(mid < tree[i]){
                    temp += tree[i] - mid;
                }
            }

            if(temp >= M){
                result = mid;
                start = mid+1; //더 길이를 높게 해서 잘라도 됨
            }else {
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}