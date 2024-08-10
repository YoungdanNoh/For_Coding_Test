import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        String Sig = st.nextToken();

        int col = N/5;
        String[][] signal = new String[5][col];
        for(int i=0; i<5; i++){
            for(int j=0; j<col; j++){
                signal[i][j] = String.valueOf(Sig.charAt(col*i+j));
            }
        }

        String[][] zero = {{"#", "#", "#"}, {"#", ".", "#"}, {"#", ".", "#"}, {"#", ".", "#"}, {"#", "#", "#"}};
        String[][] one = {{".", "#", "."}, {".", "#", "."}, {".", "#", "."}, {".", "#", "."}, {".", "#", "."}};
        String[][] two = {{"#", "#", "#"}, {".", ".", "#"}, {"#", "#", "#"}, {"#", ".", "."}, {"#", "#", "#"}};
        String[][] three = {{"#", "#", "#"}, {".", ".", "#"}, {"#", "#", "#"}, {".", ".", "#"}, {"#", "#", "#"}};
        String[][] four = {{"#", ".", "#"}, {"#", ".", "#"}, {"#", "#", "#"}, {".", ".", "#"}, {".", ".", "#"}};
        String[][] five = {{"#", "#", "#"}, {"#", ".", "."}, {"#", "#", "#"}, {".", ".", "#"}, {"#", "#", "#"}};
        String[][] six = {{"#", "#", "#"}, {"#", ".", "."}, {"#", "#", "#"}, {"#", ".", "#"}, {"#", "#", "#"}};
        String[][] seven = {{"#", "#", "#"}, {".", ".", "#"}, {".", ".", "#"}, {".", ".", "#"}, {".", ".", "#"}};
        String[][] eight = {{"#", "#", "#"}, {"#", ".", "#"}, {"#", "#", "#"}, {"#", ".", "#"}, {"#", "#", "#"}};
        String[][] nine = {{"#", "#", "#"}, {"#", ".", "#"}, {"#", "#", "#"}, {".", ".", "#"}, {"#", "#", "#"}};

        String result = "";
        for(int i=0; i<col; i++) {
            String[][] temp = new String[5][3];
            //System.out.println("i: "+i);
            if((i+2) < col){
                //3열씩 끊어서 볼 수 있음
                for(int x=0; x<5; x++){
                    for(int y=i; y<(i+3); y++){
                        temp[x][y-i] = signal[x][y];
                    }
                }

                if(Arrays.deepEquals(temp, zero)){
                    result += "0";
                    i += 2;
                }else if(Arrays.deepEquals(temp, one)) {
                	result += "1";
                    i += 1;
                }else if(Arrays.deepEquals(temp, two)){
                    result += "2";
                    i += 2;
                }else if(Arrays.deepEquals(temp, three)){
                    result += "3";
                    i += 2;
                }else if(Arrays.deepEquals(temp, four)){
                    result += "4";
                    i += 2;
                }else if(Arrays.deepEquals(temp, five)){
                    result += "5";
                    i += 2;
                }else if(Arrays.deepEquals(temp, six)){
                    result += "6";
                    i += 2;
                }else if(Arrays.deepEquals(temp, seven)){
                    result += "7";
                    i += 2;
                }else if(Arrays.deepEquals(temp, eight)){
                    result += "8";
                    i += 2;
                }else if(Arrays.deepEquals(temp, nine)){
                    result += "9";
                    i += 2;
                }

            }
        }
        
        //왼쪽 끝이 1인지 검사
        boolean flag = true;
        if(flag){
            for(int x=0; x<5; x++){
                if(signal[x][0].equals(".")){
                    //1열이 . 이라면 1이 아닌 것임
                    flag = false;
                }
            }
        }
        if(col>1) {
        	for(int x=0; x<5; x++){
	            if(signal[x][1].equals("#")){
	                //2번째 열이 # 이라면 1이 아닌 것임
	                flag = false;
	            }
	        }
        }
        
        if(flag){
        	if(col != 1) {
        		result = "1" + result;
        	}
        }
        
        //오른쪽 끝이 1인지 검사
        flag = true;
        if(flag){
            for(int x=0; x<5; x++){
                if(signal[x][col-1].equals(".")){
                    //마지막 열이 . 이라면 1이 아닌 것임
                    flag = false;
                }
            }
        }
        if(col>1) {
        	for(int x=0; x<5; x++){
	            if(signal[x][col-2].equals("#")){
	                //마지막의 바로 전의 열이 # 이라면 1이 아닌 것임
	                flag = false;
	            }
	        }
        }
        
        if(flag){
            result += "1";
        }
        
        System.out.println(result);
    }
}