import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        sc.close();

        int cnt = 0;
        for(int i = 0; i < n; i++){
            boolean flag = false;

            if (arr[i] == 1){
                continue;
            }else if(arr[i] == 2){
                cnt += 1;
            }else{
                for(int j = 2; j < arr[i]; j++){
                    if (arr[i] % j == 0){
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    cnt += 1;
                }
            }
        }
        System.out.println(cnt);
    }
}