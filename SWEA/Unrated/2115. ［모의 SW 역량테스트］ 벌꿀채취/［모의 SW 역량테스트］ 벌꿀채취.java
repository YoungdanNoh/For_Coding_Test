import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static String[] input;
	static String[] result;
	static int M;
	static int[][] arr;
	static int C;
	static String[] tmp;
	static boolean[] isSelected;
	static int p1_max;
	static int p2_max;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		//N*N C 2 -> 각각의 경우마다 오른쪽 벌통도 같이 선택
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			result = new String[2];
			p1_max = 0;
			p2_max = 0;
			ans = 0;
			
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			input = new String[N*N-N*(M-1)];
			int idx = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(j < N-(M-1)) {
						input[idx++] = i+""+j; //선택 가능한 배열 인덱스 값 저장
					}
				}
			}
			
			//2개 선택, but 겹치면 안된다.
			//ex) 4*4 벌통, M=2, {00, 01} 선택 -> {00, 01}(j1: 0 ~ 0+(M-1)), {01, 02}(j2: 1 ~ 1+(M-1)) 이므로 겹친다.
			//(j1의 마지막 값 < j2의 첫번째 값) 이어야 통과!
			combi(0, 0);
			
			//그 후 선택된 값이 C를 초과한다면 부분집합으로 최대 조합이 되는 가격을 찾는다.
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void combi(int cnt, int start) {
		if(cnt == 2) {
			int i1 = result[0].charAt(0) - '0';
			int i2 = result[1].charAt(0) - '0';
			int j1 = result[0].charAt(1) - '0';
			int j2 = result[1].charAt(1) - '0';
			if((i1==i2)&&(j1 + (M-1) >= j2)) {
				//벌통이 겹친다.
				return;
			}
			
			//각각 두 일꾼이 채취할 수 있는 꿀의 양 계산
			int p1=0, p2=0;
			int p1_earn=0, p2_earn=0;
			for(int j=j1; j<j1+M; j++) {
				p1 += arr[i1][j];
				p1_earn += arr[i1][j]*arr[i1][j];
			}
			for(int j=j2; j<j2+M; j++) {
				p2 += arr[i2][j];
				p2_earn += arr[i2][j]*arr[i2][j];
			}
			
			//그 후 선택된 값이 C를 초과한다면 부분집합으로 최대 조합이 되는 가격을 찾는다.
			if(p1 > C) {
				tmp = new String[M];
				for(int i=0; i<M; i++) {
					tmp[i] = i1 + "" + (j1+i);
				}
				isSelected = new boolean[M];
				p1_max = 0;
				subSet(0, 1);
			}else {
				p1_max = p1_earn;
			}
			
			if(p2 > C) {
				tmp = new String[M];
				for(int i=0; i<M; i++) {
					tmp[i] = i2 + "" + (j2+i);
				}
				isSelected = new boolean[M];
				p2_max = 0;
				subSet(0, 2);
			}else {
				p2_max = p2_earn;
			}
			ans = Math.max(ans, p1_max+p2_max);
			return;
		}
		
		for(int i=start; i<input.length; i++) {
			result[cnt] = input[i];
			combi(cnt+1, i+1);
		}
	}
	
	private static void subSet(int cnt, int flag) {
		if(cnt == M) {
			int sum = 0;
			int earn = 0;
			int x = 0;
			int y = 0;
			for(int i=0; i<M; i++) {
				if(isSelected[i]) {
					x = tmp[i].charAt(0) - '0';
					y = tmp[i].charAt(1) - '0';
					sum += arr[x][y];
					earn += arr[x][y]*arr[x][y];
				}
			}
			if(sum <= C) {
				if(flag == 1) {
					p1_max = Math.max(p1_max, earn);
				}else {
					p2_max = Math.max(p2_max, earn);
				}
			}
			return;
		}
		if(flag == 1) {
			isSelected[cnt] = true;
			subSet(cnt+1, 1);
			isSelected[cnt] = false;
			subSet(cnt+1, 1);
		}else {
			isSelected[cnt] = true;
			subSet(cnt+1, 2);
			isSelected[cnt] = false;
			subSet(cnt+1, 2);
		}
	}
}