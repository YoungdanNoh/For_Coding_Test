import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Node>[] node;

    static class Node implements Comparable<Node>{
        int next;
        double w;
        
        public Node(int next, double w){
            this.next = next;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", w=" + w +
                    '}';
        }

        @Override
        public int compareTo(Node o){
            if(this.w > o.w){
                return 1;
            }else if(this.w == o.w){
                return 0;
            }else {
                return -1;
            }

        }
    }

    static class Point{
        double x;
        double y;

        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine().trim());
        Point[] p = new Point[N];
        node = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            p[i] = new Point(x, y);

            node[i] = new ArrayList<>();
        }


        for (int i = 0; i < N; i++) {
            double x = p[i].x;
            double y = p[i].y;

            for (int j = i+1; j < N; j++) {
                double x2 = p[j].x;
                double y2 = p[j].y;

                double dist = Math.sqrt(Math.pow((x-x2), 2) + Math.pow((y-y2), 2));
                node[i].add(new Node(j, dist)); //다음 별까지의 가중치
                node[j].add(new Node(i, dist)); //다음 별까지의 가중치
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(node[i].toString());
//        }

        double cost = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        boolean[] visited = new boolean[N];
        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.next]) continue;

            visited[cur.next] = true;
            cost += cur.w;

            for(Node n : node[cur.next]){
                if(!visited[n.next]){
                    pq.offer(n);
                }
            }
        }

        System.out.println(Math.floor(cost*100)/100);
    }
}