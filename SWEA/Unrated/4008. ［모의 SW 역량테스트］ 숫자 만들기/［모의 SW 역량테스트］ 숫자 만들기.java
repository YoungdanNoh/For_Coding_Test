import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int max;
	static int min;
	static int N;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			max = -100000000;
			min = 100000000;
			
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			
			int[] op = new int[4];
			num = new int[N];
			st = new StringTokenizer(bf.readLine());
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(op, num[0], 1);
			System.out.println("#"+t+" "+(max-min));
		}
	}
	
	private static void dfs(int[] op, int sum, int n) {
		if(n == N) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		
		if(op[0] > 0) {
			int[] tmp = op.clone();
			tmp[0] -= 1;
			dfs(tmp, sum+num[n], n+1);
		}
		if(op[1] > 0) {
			int[] tmp = op.clone();
			tmp[1] -= 1;
			dfs(tmp, sum-num[n], n+1);
		}
		if(op[2] > 0) {
			int[] tmp = op.clone();
			tmp[2] -= 1;
			dfs(tmp, sum*num[n], n+1);
		}
		if(op[3] > 0) {
			int[] tmp = op.clone();
			tmp[3] -= 1;
			dfs(tmp, sum/num[n], n+1);
		}
	}

}