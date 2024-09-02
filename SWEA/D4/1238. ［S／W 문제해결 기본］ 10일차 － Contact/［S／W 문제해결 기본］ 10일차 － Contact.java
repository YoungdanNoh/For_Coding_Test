import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static ArrayList<ArrayList<Integer>> call;
	static int S;
	static boolean[] visited;
	static ArrayList<int[]> store;
	static int max_b;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			call = new ArrayList<>(); //0번 인덱스는 안 씀
			visited = new boolean[101];
			store = new ArrayList<>();
			max_b = 0;
			ans = 0;
			
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			visited[S] = true;
			
			for(int i=0; i<101; i++) {
				call.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(bf.readLine());
			for(int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if(!call.get(from).contains(to)) {
					call.get(from).add(to); //해당 인덱스 번호에 담겨있는 값이 연락할 수 있는 번호
				}
			}
            
			bfs();
			for(int i=0; i<store.size(); i++) {
				if(store.get(i)[1] == max_b) {
					ans = Math.max(ans, store.get(i)[0]);
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<call.get(S).size(); i++) {
			q.offer(new int[] {call.get(S).get(i), 0});
			visited[call.get(S).get(i)] = true;
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
            
			for(int i=0; i<call.get(cur[0]).size(); i++) {
				if(!visited[call.get(cur[0]).get(i)]) {
					//아직 방문하지 않았다면 큐에 넣기
					q.offer(new int[] {call.get(cur[0]).get(i), cur[1]+1});
					visited[call.get(cur[0]).get(i)] = true;
					max_b = Math.max(max_b, cur[1]+1);
				}
			}
			store.add(cur);
		}
	}

}