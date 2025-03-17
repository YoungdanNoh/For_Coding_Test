import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine().trim());

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < N; i++) {
            String title = bf.readLine().trim();

            if (map.containsKey(title)) {
                map.put(title, map.get(title) + 1);

            }else{
                map.put(title, 1);
            }
        }

        ArrayList<String> list = new ArrayList<String>(map.keySet());
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1)==map.get(o2)){
                    return o1.compareTo(o2);
                }

                return map.get(o2) - map.get(o1);
            }
        }); // 내림차순

        System.out.println(list.get(0));
    }
}
