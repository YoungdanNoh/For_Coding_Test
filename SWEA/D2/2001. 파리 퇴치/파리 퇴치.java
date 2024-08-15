import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=tc; t++) {
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N]; //파리 위치 저장
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] prefix = new int[N+1][N+1]; //파리 위치 저장
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					prefix[i][j] = arr[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
				}
			}
			
			for(int i=M; i<=N; i++) {
				for(int j=M; j<=N; j++) {
					int temp = prefix[i][j] - prefix[i-M][j] - prefix[i][j-M] + prefix[i-M][j-M];
					ans = Math.max(temp, ans);
				}
			}
			
			System.out.println("#" + t + " " + ans);
			ans = 0;
		}
	}

}