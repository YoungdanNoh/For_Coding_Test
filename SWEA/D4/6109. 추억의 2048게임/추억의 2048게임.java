import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			String direc = st.nextToken();
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			switch (direc) {
			case "left":
				left();
				break;
			case "right":
				right();
				break;
			case "up":
				up();
				break;
			case "down":
				down();
				break;
			}
			
			System.out.println("#"+t);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	private static void left() {
		//왼쪽 방향으로 0없애기
		for (int i=0; i<N; i++) {
			int index = 0;
			
			for (int j=0; j<N; j++) {
				if (map[i][j] != 0) {
					map[i][index] = map[i][j];
					if (index != j) {
						map[i][j] = 0;
					}
					index++;
				}
			}
		}
				
		for(int i=0; i<N; i++) {
			for(int j=1; j<N; j++) {
				if(map[i][j-1] == map[i][j]) {
					map[i][j-1] = map[i][j-1]*2;
					for(int k=j; k<N-1; k++) {
						map[i][k] = map[i][k+1];
					}
					map[i][N-1] = 0;
				}
			}
		}
		
	}
	
	private static void right() {
		//오른쪽 방향으로 0없애기
		for (int i = 0; i < N; i++) {
			int index = N-1;
			
			for (int j=N-1; j>=0; j--) {
				if (map[i][j] != 0) {
					map[i][index] = map[i][j];
					if (index != j) {
						map[i][j] = 0;
					}
					index--;
				}
			}
		}
				
		for(int i=N-1; i>=0; i--) {
			for(int j=N-2; j>=0; j--) {
				if(map[i][j+1] == map[i][j]) {
					map[i][j+1] = map[i][j+1]*2;
					for(int k=j; k>=1; k--) {
						map[i][k] = map[i][k-1];
					}
					map[i][0] = 0;
				}
			}
		}
	}

	private static void up() {
		//위 방향으로 0없애기
		for (int j=0; j<N; j++) {
			int index = 0;
			for (int i=0; i<N; i++) {
				if (map[i][j] != 0) {
					map[index][j] = map[i][j];
					if (index != i) {
						map[i][j] = 0;
					}
					index++;
				}
			}
		}
		
		for(int j=0; j<N; j++) {
			for(int i=1; i<N; i++) {
				if(map[i][j] == map[i-1][j]) {
					map[i-1][j] = map[i-1][j]*2;
					for(int k=i; k<N-1; k++) {
						map[k][j] = map[k+1][j];
					}
					map[N-1][j] = 0;
				}
			}
		}
	}
	
	private static void down() {
		//아래 방향으로 0없애기
		for (int j=0; j<N; j++) {
			int index = N-1;
			for (int i=N-1; i>=0; i--) {
				if (map[i][j] != 0) {
					map[index][j] = map[i][j];
					if (index != i) {
						map[i][j] = 0;
					}
					index--;
				}
			}
		}
				
		for(int j=0; j<N; j++) {
			for(int i=N-2; i>=0; i--) {
				if(map[i][j] == map[i+1][j]) {
					map[i+1][j] = map[i+1][j]*2;
					for(int k=i; k>=1; k--) {
						map[k][j] = map[k-1][j];
					}
					map[0][j] = 0;
				}
			}
		}
	}

}