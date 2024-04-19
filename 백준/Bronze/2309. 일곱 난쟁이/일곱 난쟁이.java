import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int[] tall = new int[9];
        int sum = 0;

        for(int i = 0; i < 9; i++){
            tall[i] = sc.nextInt();
            sum += tall[i];
        }

        int temp;
        boolean flag = false;
        int x, y = 0;
        Arrays.sort(tall);
        
        for(x = 0; x < 8; x++){
            for(y = x+1; y < 9; y++){
                temp = sum - tall[x] - tall[y];
                if (temp == 100){
                    flag = true;
                    break;
                }
            }
            if (flag){
                break;
            }
        }

        for(int j = 0; j < 9; j++){
            if (j != x && j != y){
                System.out.println(tall[j]);
            }
        }
    }
}
