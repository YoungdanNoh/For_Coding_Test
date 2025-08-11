import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node{
        int next;
        int cnt;

        public Node(int next, int cnt){
            this.next = next;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        ArrayList<Node>[] graph = new ArrayList[N+1];
        for(int i=0;i<N+1;i++){
            graph[i] = new ArrayList<Node>();
        }

        int[] indegree = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            indegree[x]++;
            graph[y].add(new Node(x, k));
        }

        ArrayList<Integer> base = new ArrayList<>();
        int[][] result = new int[N+1][N+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            if(indegree[i]==0){
                result[i][i] = 1; // 기본 부품에는 1 넣어주기
                pq.add(i);
                base.add(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();

            for(Node next: graph[cur]){
                // 현재 cur이 있어야 하는 next 부품
                indegree[next.next]--;
                for(int i=0; i<base.size(); i++){
                    // next를 만들 때 필요한 기본 부품의 수는
                    // cur을 만들 때 필요한 기본 부품의 수에 *cnt 만큼을 해준 것이다.
                    result[next.next][base.get(i)] += result[cur][base.get(i)] * next.cnt;
                }

                if(indegree[next.next]==0){
                    pq.add(next.next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<base.size(); i++){
            if(result[N][base.get(i)] > 0){
                sb.append(base.get(i) + " " + result[N][base.get(i)]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
