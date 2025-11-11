import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        TreeSet<Integer> set = new TreeSet<>();

        // 명소(1) 위치만 TreeSet에 저장 (0-based)
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == 1) set.add(i);
        }

        int dir = 0; // 도현이의 위치 (0-based)
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int t = Integer.parseInt(st.nextToken());

            if (t == 1) { // 명소 토글
                int x = Integer.parseInt(st.nextToken()) - 1;
                if (arr[x] == 0) {
                    arr[x] = 1;
                    set.add(x);
                } else {
                    arr[x] = 0;
                    set.remove(x);
                }

            } else if (t == 2) { // 이동
                int k = Integer.parseInt(st.nextToken());
                dir = (dir + (k % N) + N) % N;

            } else { // 3: 최소 이동 거리 출력
                if (set.isEmpty()) {
                    sb.append(-1).append('\n');
                    continue;
                }

                if (arr[dir] == 1) {
                    sb.append(0).append('\n');
                    continue;
                }

                Integer p = set.ceiling(dir);
                if (p != null) {
                    sb.append(p - dir).append('\n');
                } else {
                    sb.append(N - dir + set.first()).append('\n');
                }
            }
        }

        System.out.print(sb.toString());
    }
}
