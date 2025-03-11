import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] parent;

    public static int find(int v){
        // 부모 찾기
        if(v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    public static void union(int v1, int v2){
        int rootA = find(v1);
        int rootB = find(v2);

        if(rootA < rootB){
            // 값이 더 작은 쪽의 부모로 합쳐준다.
            parent[rootB] = rootA;
        }else {
            parent[rootA] = rootB;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for (int i = 0; i < n+1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine().trim());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0){
                // 집합에 추가
                union(b, c);
            }else{
                // 같은 집합인지 검사
                int rootA = find(b);
                int rootB = find(c);

                if(rootA == rootB){
                    System.out.println("YES");
                }else {
                    System.out.println("NO");
                }
            }
        }
    }
}
