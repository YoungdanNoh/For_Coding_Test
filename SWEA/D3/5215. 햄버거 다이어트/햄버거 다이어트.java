import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int n;
	static int cal;
	static int[][] scal;
	static int[] result;
	static int rimit = 0;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int tc = Integer.parseInt(st.nextToken()); //tc 갯수
		
		for(int t=1; t<=tc; t++) {
			st = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(st.nextToken()); //재료의 수
			cal = Integer.parseInt(st.nextToken()); //제한 칼로리
			
			result = new int[n];
			scal = new int[n][2];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(bf.readLine());
				scal[i][0] = Integer.parseInt(st.nextToken());
				scal[i][1] = Integer.parseInt(st.nextToken());
				//순서대로 맛에 대한 점수, 칼로리
			}
			
			for(int i=1; i<=n; i++) {
				rimit = i; //1개 ~ n개 선택가능
				combi(0, 0);
			}
			
			System.out.println("#" + t + " " + ans);
			ans = 0;
		}
	}

	private static void combi(int cnt, int start) {
		if(cnt == rimit) {
			int flavor = 0;
			int caltemp = 0;
			for(int j=0; j<rimit; j++) {
				flavor += scal[result[j]][0]; //맛 더하기
				caltemp += scal[result[j]][1];
			}
			if(caltemp <= cal) {
				//제한 칼로리 이하라면
				ans = Math.max(ans, flavor); //더 점수가 높은 맛으로 바꾸기
			}
			
			
			return;
		}
		for(int i=start; i<n; i++) {
			result[cnt] = i; //i번째 재료를 선택
			combi(cnt+1, i+1);
		}
	}

}