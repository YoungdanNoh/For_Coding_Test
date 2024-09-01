import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(bf.readLine().trim());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(bf.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            //Kruskal's algorithm
            edges = new Edge[E];
            for(int i=0; i<E; i++){
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                int weight = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(start, end, weight);
            }

            make();
            Arrays.sort(edges);

            long cost = 0;
            int cnt = 0;
            for(Edge e : edges){
                if(union(e.start, e.end)){
                    cost += e.weight;
                    if(++cnt == V-1) break;
                }
            }

            System.out.println("#" + t + " " + cost);
        }
    }
}