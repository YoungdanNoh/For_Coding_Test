import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			ans = 0;
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] parm = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				String tmp = st.nextToken();
				for(int j=0; j<N; j++) {
					parm[i][j] = tmp.charAt(j) - '0';
				}
			}
			
			
			int mid = N/2; //중간 idx
			for(int i=0; i<N; i++) {
				ans += parm[i][mid];
			}
			//ans += parm[mid][0]; //맨 왼쪽
			//ans += parm[mid][N-1]; //맨 오른쪽
				
			int x = mid;
			int iter = 1;
			for(int i=0; i<mid; i++) {
				for(int j=0; j<iter; j++) {
					ans += parm[x+j][i]; //왼쪽 부분
					ans += parm[x+j][N-i-1]; //오른쪽 부분
				}
				iter += 2;
				x-=1;
			}
			
			
			System.out.println("#" + t + " " + ans);
		}
	}

}
