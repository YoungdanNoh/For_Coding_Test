import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

        String in = sc.nextLine();
        sc.close();

        String[] arr = in.split(" ");
        ArrayList<Integer> pars = new ArrayList<>();

        for(int i=0; i<=1; i++){
            pars.add(Integer.parseInt(arr[i]));
        }

        // 가위:1, 바위:2, 보:3
        if (pars.get(0) == 1){
            if(pars.get(1) == 2){
                System.out.println("B");
            }else if(pars.get(1) == 3){
                System.out.println("A");
            }
        }else if(pars.get(0) == 2){
            if(pars.get(1) == 1){
                System.out.println("A");
            }else if(pars.get(1) == 3){
                System.out.println("B");
            }
        }else if(pars.get(0) == 3){
            if(pars.get(1) == 1){
                System.out.println("B");
            }else if(pars.get(1) == 2){
                System.out.println("A");
            }
        }
        
	}
}