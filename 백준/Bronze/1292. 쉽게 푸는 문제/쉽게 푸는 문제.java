import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();
        ArrayList<Integer> arr = new ArrayList<Integer>();

        int cnt = 1;
        while (arr.size() < b){
            for(int i = 0; i < cnt; i++){
                arr.add(cnt);
            }
            cnt += 1;
        }

        int sum = 0;
        for(int i = a-1; i < b; i++){
            sum += arr.get(i);
        }
        System.out.println(sum);
    }
}