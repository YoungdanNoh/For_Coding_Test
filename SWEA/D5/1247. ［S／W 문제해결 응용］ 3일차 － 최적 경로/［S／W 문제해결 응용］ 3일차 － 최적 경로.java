import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] company;
	static int[] home;
	static int N;
	static int[][] client;
	static int[] result;
	static boolean[] isSelected;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			company = new int[2];
			home = new int[2];
			
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			client = new int[N+1][2];
			result = new int[N];
			isSelected = new boolean[N];
			ans = 100*(N+2);
			
			st = new StringTokenizer(bf.readLine());
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for(int i=1; i<N+1; i++) {
				client[i][0] = Integer.parseInt(st.nextToken());
				client[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 회사 -> 모든 고객 방문 -> 집
			perm(0, 0);
			System.out.println("#"+t+" "+ans);
		}
	}

	private static void perm(int cnt, int sum) {
		if(ans < sum) {
			return;
		}
		
		if(cnt == N) {
			sum += Math.abs(home[0] - client[result[N-1]][0]) 
					+ Math.abs(home[1] - client[result[N-1]][1]);
			
			ans = Math.min(ans, sum);
			
			return;
		}
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			
			result[cnt] = i+1;
			isSelected[i] = true;
			int tmp = 0;
			
			if(cnt == 0) {
				tmp += Math.abs(company[0] - client[result[0]][0]) 
						+ Math.abs(company[1] - client[result[0]][1]);
			}else {
				tmp += Math.abs(client[result[cnt-1]][0] - client[result[cnt]][0]) 
						+ Math.abs(client[result[cnt-1]][1] - client[result[cnt]][1]);
			}
			perm(cnt + 1, sum+tmp);
			
			isSelected[i] = false;
		}
	}

}