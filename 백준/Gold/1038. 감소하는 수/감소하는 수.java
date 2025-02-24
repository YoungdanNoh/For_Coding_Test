import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Long> list = new ArrayList<Long>();
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine().trim());
        N = Integer.parseInt(st.nextToken());

        //list.add(0);

        for (int i = 1; i <= 10; i++) {
            arr = new String[i];
            perm(0, 0, i);
        }

        Collections.sort(list);

        if(list.size() <= N) {
            System.out.println(-1);
        }else{
            System.out.println(list.get(N));
        }

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
    }

    public static void perm(int cnt, int start, int end) {
        if (cnt == end) {
            String result = "";

            for (int i = end-1; i >=0; i--) {
                result += arr[i];
            }
            list.add(Long.parseLong(result));
            return;
        }

        for (int i = 9; i >= start; i--) {
            arr[cnt] = i + "";
            perm(cnt + 1, i+1, end);
        }
    }

}
