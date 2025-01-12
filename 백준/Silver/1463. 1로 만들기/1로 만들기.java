import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine().trim());
		
		int[] arr = new int[N+1];
		for(int i=2; i<N+1; i++) {
			arr[i] = arr[i-1] + 1; //1을 빼는 경우
					
			if(i%2 == 0) {
				//2로 나누어 떨어지는 경우
				arr[i] = Math.min(arr[i], arr[i/2] + 1);
			}
			
			if(i%3 == 0) {
				//3으로 나누어 떨어지는 경우
				arr[i] = Math.min(arr[i], arr[i/3] + 1);
			}
		}
		
		System.out.println(arr[N]);
	}

}