import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int V;
    static int E;
    static int[] parents;
    static Edge[] edges;

    static void make(){
        parents = new int[V];
        for(int i=0; i<V; i++){
            parents[i] = i;
        }
    }
    static int findSet(int a){
        if(parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }
    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static class Edge2 implements Comparable<Edge2>{
        int node;
        int weight;

        public Edge2(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge2 o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Edge2{" +
                    "node=" + node +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            //Kruskal's algorithm
//            edges = new Edge[E];
//            for(int i=0; i<E; i++){
//                st = new StringTokenizer(bf.readLine());
//                int start = Integer.parseInt(st.nextToken())-1;
//                int end = Integer.parseInt(st.nextToken())-1;
//                int weight = Integer.parseInt(st.nextToken());
//                edges[i] = new Edge(start, end, weight);
//            }
//
//            make();
//            Arrays.sort(edges);
//
//            long cost = 0;
//            int cnt = 0;
//            for(Edge e : edges){
//                if(union(e.start, e.end)){
//                    cost += e.weight;
//                    if(++cnt == V-1) break;
//                }
//            }
//
//            System.out.println("#" + t + " " + cost);

            //Prim's algorithm
            //Edge2[] edges = new Edge2[V];
            ArrayList<Edge2>[] edges = new ArrayList[V];
            for(int i=0; i<V; i++) edges[i] = new ArrayList<>();

            for(int i=0; i<E; i++){
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                int weight = Integer.parseInt(st.nextToken());
                edges[start].add(new Edge2(end, weight));
                edges[end].add(new Edge2(start, weight));
            }

            boolean[] visit = new boolean[V];

            PriorityQueue<Edge2> pq = new PriorityQueue<>();
            pq.offer(edges[0].get(0));

            long cost = 0;
            int cnt = 0;
            while(!pq.isEmpty()){
                Edge2 e = pq.poll();

                if(visit[e.node]) continue;

                visit[e.node] = true;
                if(cnt != 0) cost += e.weight;

                if(++cnt == V) break;

                for(Edge2 edge: edges[e.node]){

                    if(!visit[edge.node]){
                        pq.offer(edge);
                    }
                }
            }

            System.out.println("#" + t + " " + cost);
        }
    }
}