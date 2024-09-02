import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int[][] city;
	static int K;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(bf.readLine().trim());
		for(int t=1; t<=T; t++) {
			ans = 0;
			
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			city = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					city[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			K = 2*N;
			for(int si=0; si<N; si++) {
				for(int sj=0; sj<N; sj++) {
					//모든 시작 위치에 대하여
					for(int k=0; k<K; k++) {
						//k를 늘려가보면서 돌리기
						int cnt = 0;
						for(int i=(si-k+1); i<=(si+k-1); i++) {
							for(int j=(sj-k+1 + Math.abs(si-i)); j<=(sj+k-1 - Math.abs(si-i)); j++) {
								if(i>=0 && i<N && j>=0 && j<N) {
									//범위 내라면
									if(city[i][j] == 1) {
										//서비스를 받을 수 있는 집이 있다면
										cnt++;
									}
								}
							}
						}
						int profit = cnt*M - (k*k + (k-1)*(k-1));
						if(profit >= 0) {
							ans = Math.max(ans, cnt);
						}
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}

}
