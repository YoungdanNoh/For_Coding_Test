import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(bf.readLine().trim());
        N = Integer.parseInt(st.nextToken()); //N일 동안 사용
        int M = Integer.parseInt(st.nextToken()); //M번 인출
        
        arr = new int[N];
        int start = 1; //최소 인출 금액
        int end = 100000*10000; //한번에 인출해야 하는 최대 금액
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine().trim());
            start = Math.max(start, arr[i]);
        }

        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            //mid만큼씩 인출한다고 가정
            if(M >= withdraw(mid)){
                //M번 보다 더 적게 인출해도 됨
                //따라서 인출 금액을 줄여서 다시 한번 테스트
                result = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        sb.append(result);
        System.out.println(sb);
    }

    private static int withdraw(int mid) {
        int cnt = 1; //첫 날도 인출하여 사용해야 하므로 1
        int money = mid;

        for (int i = 0; i < N; i++) {
            money -= arr[i]; //해당 날짜의 사용 금액 마이너스

            if(money < 0) {
                //돈이 부족. 다시 인출
                cnt++;
                money = mid - arr[i];
            }
        }

        return cnt;
    }
}