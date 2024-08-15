import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N]; //1~N까지의 자연수 배열
		result = new int[N];
		for(int i=1; i<=N; i++) {
			arr[i-1] = i;
		}
		
		combi(0, 0);
	}

	private static void combi(int cnt, int start) {
		if(cnt == M) {
			for(int i=0; i<N; i++) {
				if(result[i] != 0) {
					System.out.print(result[i] + " ");
				}
			}
			System.out.println();
			
			return;
		}
		for(int i=start; i<N; i++) {
			result[i] = arr[i];
			combi(cnt+1, i+1);
			result[i] = 0;
		}
	}

}