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
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 1; //최소 거리
        int end = arr[N-1] - arr[0]; //최대거리
        int result = 0;

        //가장 인접한 두 공유기 사이 최소 거리 = mid
        while (start <= end){
            int mid = (start+end) / 2;

            int linstall = 0; //가장 최근에 설치한 곳
            int cnt = 1; //설치한 공유기 수
            for(int i=1; i<N; i++){
                if((arr[i] - arr[linstall]) >= mid){
                    linstall = i; //가장 인접한 두 공유기 사이 최소 거리인 mid 값 이상이므로
                    //i번째 위치에 설치한다.
                    cnt++;
                }
            }
            if(cnt >= C){
                //설치한 공유기 수가 설치 가능한 공유기 갯수보다 많거나 크다면
                //최소 거리를 늘려 공유기 갯수를 줄여본다.
                //(같다면 더 최적의 값을 찾기 위해 거리를 늘려본다.)
                start = mid+1;
                result = mid;
            }else{
                //설치한 공유기 갯수가 작다면 거리를 줄여서 더 설치해본다.
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}