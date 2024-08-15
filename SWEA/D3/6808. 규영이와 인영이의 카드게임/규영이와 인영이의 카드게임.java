import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Solution {
	
	static int[] result = new int[9];
	static boolean isSelected[] = new boolean[9];
	static int[] gy = new int[9]; //주어지는 순서대로 카드를 낸다.
	static int[] iy = new int[9];
	static int gyw = 0; //규영이가 이기는 경우의 수
	static int gyl = 0; //규영이가 지는 경우의 수

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		int t = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=t; i++) {
			//테스트 케이스
			st = new StringTokenizer(bf.readLine());
			for(int c=0; c<9; c++) {
				gy[c] = Integer.parseInt(st.nextToken());;
			}
			
			int cnt = 0;
			for(int c=1; c<19; c++) {
				int temp = c;
				if(!IntStream.of(gy).anyMatch(x -> x == temp)) {
					iy[cnt++] = c;
				}
			}
			
			perm(0);
			System.out.println("#" + i + " " + gyw + " " + gyl);
			gyw = 0;
			gyl = 0;
		}
	}
	
	private static void perm(int cnt) {
		if(cnt == 9) {
			//System.out.println(Arrays.toString(result));
			int gys = 0; //규영이의 점수
			int iys = 0; //인영이의 점수
			for(int i=0; i<9; i++) {
				if(gy[i] > result[i]) {
					//규영이의 카드 숫자가 더 크다면
					//규영이의 점수 += 두 카드에 적힌 수의 합
					gys += gy[i] + result[i];
				}else if(gy[i] < result[i]) {
					//인영이의 카드 숫자가 더 크다면
					//인영이의 점수 += 두 카드에 적힌 수의 합
					iys += gy[i] + result[i];
				}
			}
			if(gys > iys) {
				//규영이가 이겼다면
				gyw += 1;
			}else if(gys < iys) {
				//규영이가 졌다면
				gyl += 1;
			}
			
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(isSelected[i]) continue;
			
			result[cnt] = iy[i];
			isSelected[i] = true;
			
			perm(cnt+1);
			isSelected[i] = false;
		}
	}
}