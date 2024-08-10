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

        int[] arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int diff = arr[0] + arr[N-1]; //두 값 합
        int start = 0;
        int end = N-1;
        int rs = 0; //결과 시작 인덱스
        int re = N-1; //결과 종료 인덱스

        while(start < end){
            //start 인덱스가 end보다 커지면 의미없음
            int temp = arr[start] + arr[end];

            if(Math.abs(diff) > Math.abs(temp)){
                //두 값 합의 절댓값이 현재 diff 값보다 작다면 해당 값으로 교체
                diff = temp;
                rs = start;
                re = end;
            }
            if(temp == 0){
                //가장 좋은 경우
                rs = start;
                re = end;
                break;
            }else if(temp < 0){
                //합이 0보다 작으므로 시작 인덱스 증가
                //System.out.println(start);
                start++;
            }else {
                //합이 0보다 크므로 종료 인덱스 감소
                end--;
            }
        }
        System.out.println(arr[rs] + " " + arr[re]);
    }
}