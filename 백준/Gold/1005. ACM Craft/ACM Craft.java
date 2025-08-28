import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine());

        for(int t=0; t<T; t++) {
            st = new StringTokenizer(bf.readLine());

            int N = Integer.parseInt(st.nextToken()); // 건물의 개수
            int K = Integer.parseInt(st.nextToken()); // 건설 순서 규칙의 개수

            ArrayList<Integer>[] graph = new ArrayList[N+1];
            for(int i=0; i<=N; i++) {
                graph[i] = new ArrayList<>();
            }

            int[] time = new int[N+1]; // 각 건물 당 건설에 걸리는 시간
            st = new StringTokenizer(bf.readLine());
            for(int i=1; i<=N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            int[] indegree = new int[N+1];
            for(int i=0; i<K; i++) {
                // 건설 순서 규칙
                st = new StringTokenizer(bf.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v); // v를 건설하기 위해선 u가 건설되어 있어야 함
                indegree[v]++;
            }

            int W = Integer.parseInt(bf.readLine());

            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> Integer.compare(a[0], b[0])
            ); // [건설 시간, 건물 번호]
            for(int i=1; i<=N; i++) {
                if(indegree[i] == 0) {
                    pq.add(new int[] {time[i], i});
                }
            }

            boolean[] visited = new boolean[N+1];
            int ans = 0;
            while(!pq.isEmpty()) {
                int[] cur = pq.poll();

                if(visited[cur[1]]) {
                    // 이미 지은 건물
                    continue;
                }

                visited[cur[1]] = true;

                if(cur[1] == W){
                    // 지어야 할 건물을 지음
                    ans = cur[0];
                    break;
                }

                for(int next : graph[cur[1]]) {
                    if(!visited[next]) {
                        indegree[next]--;
                        if(indegree[next] == 0) {
                            pq.add(new int[] {cur[0] + time[next], next});
                        }
                    }
                }
            }

            System.out.println(ans);
        }
    }
}
