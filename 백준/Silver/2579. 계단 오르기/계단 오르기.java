import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		
		int[] stair = new int[cnt];
		for(int i=0; i<cnt; i++) {
			st = new StringTokenizer(bf.readLine());
			stair[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[cnt];
		
		if(cnt==1) {
			//dp[0] = stair[0];
			System.out.println(stair[0]);
		}else if(cnt==2) {
			System.out.println(stair[0] + stair[1]);
		}else {
			dp[0] = stair[0];
			dp[1] = stair[0] + stair[1];
			dp[2] = Math.max((stair[0] + stair[2]), (stair[1] + stair[2]));
			for(int i=3; i<cnt; i++) {
				dp[i] = Math.max(dp[i-2]+stair[i], dp[i-3]+stair[i-1]+stair[i]);
			}
			System.out.println(dp[cnt-1]);
		}
	}
}