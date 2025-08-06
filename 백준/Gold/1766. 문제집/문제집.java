import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        int[] indegree = new int[N+1];

        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            indegree[B] += 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            if(indegree[i] == 0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            System.out.print(cur + " ");

            for(int i=0; i<graph[cur].size(); i++){
                indegree[graph[cur].get(i)] -= 1; // 내가 가리키는 애들의 indegree를 1 감소
                if(indegree[graph[cur].get(i)] == 0){
                    pq.add(graph[cur].get(i));
                }
            }
        }

    }
}
