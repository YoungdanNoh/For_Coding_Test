import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int[] parents;
	static String ans;
	
	static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(bf.readLine().trim());
		for(int t=1; t<=T; t++) {
			ans = "";
			
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			make();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(bf.readLine());
				if(Integer.parseInt(st.nextToken()) == 0) {
					//합집합 만들기
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					union(a, b);
				}else {
					//두 원소가 같은 집합인지 확인
					int a = findSet(Integer.parseInt(st.nextToken()));
					int b = findSet(Integer.parseInt(st.nextToken()));
					if(a == b) ans+="1";
					else ans+="0";
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}

}