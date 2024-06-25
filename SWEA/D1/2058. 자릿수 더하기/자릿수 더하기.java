import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        String in = sc.next();
        sc.close();

        int result = 0;
        for(String i : in.split("")){
            result += Integer.parseInt(i);
        }
        System.out.println(result);
	}
}