import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine());

        TreeMap<Integer, TreeSet<Integer>> treeMap = new TreeMap<>(); // 문제 난이도, Node
        HashMap<Integer, Integer> map = new HashMap<>(); // 문제 번호, 난이도

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            if(treeMap.containsKey(L)) {
                treeMap.get(L).add(P);
            }else{
                TreeSet<Integer> ts = new TreeSet<>();
                ts.add(P);
                treeMap.put(L, ts);
            }

            map.put(P, L);
        }

        int M = Integer.parseInt(bf.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            String command = st.nextToken();

            if(command.equals("add")) {
                // 문제 추가

                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());

                if(treeMap.containsKey(L)) {
                    treeMap.get(L).add(P);
                }else{
                    TreeSet<Integer> ts = new TreeSet<>();
                    ts.add(P);
                    treeMap.put(L, ts);
                }

                map.put(P, L);

            }else if(command.equals("recommend")) {
                // 문제 추천
                int x = Integer.parseInt(st.nextToken());

                if(x == -1){
                    // 추천 문제 리스트에서 가장 쉬운 문제의 번호를 출력
                    // 만약 가장 쉬운 문제가 여러 개라면 문제 번호가 작은 것으로 출력
                    int key = treeMap.firstKey();
                    TreeSet<Integer> ts = treeMap.get(key);
                    System.out.println(ts.first());

                }else{
                    // 추천 문제 리스트에서 가장 어려운 문제의 번호를 출력
                    // 만약 가장 어려운 문제가 여러 개라면 문제 번호가 큰 것으로 출력
                    int key = treeMap.lastKey();
                    TreeSet<Integer> ts = treeMap.get(key);
                    System.out.println(ts.last());

                }

            }else{
                // solved일 때 문제 삭제
                int p = Integer.parseInt(st.nextToken()); // 문제 번호
                int l = map.get(p); // 난이도

                TreeSet<Integer> ts = treeMap.get(l);
                ts.remove(p);
                if(ts.size() == 0){
                    treeMap.remove(l);
                }

                map.remove(p);

            }
        }

    }

}
