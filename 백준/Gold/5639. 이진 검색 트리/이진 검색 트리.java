import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while(true){
            String str = br.readLine();

            // Java 17의 경우 str값을 인식을 못해서 아래 구문으로 넘어가지 못함
            if(str == null || str.equals("")) break;

            int val = Integer.parseInt(str);

            root.insert(val); // 한줄씩 입력을 받으며 루트보다 작은 값은 왼쪽으루
                              // 큰 값은 오른쪽으로 보낸다.
        }

        postOrder(root); // root에서 부터 시작하여 후위 순회 시작점을 찾아간다.
                         // 왼쪽-오른쪽-root
    }

    static void postOrder(Node curr){
        if(curr == null) return; // 맨 끝에 있는 왼쪽 or 오른쪽 노드를 다 탐색했다면 return

        postOrder(curr.left); // 가장 왼쪽에 있는 노드를 쭉 찾아간다.
        postOrder(curr.right); // 가장 오른쪽에 있는 노드를 쭉 찾아간다.
        System.out.println(curr.val); // 노드 키 출력
    }

    static class Node{
        int val;
        Node left, right;

        Node(int val){
            this.val = val;
        }

        void insert(int n){
            if(n < this.val){
                if(this.left == null) this.left = new Node(n); 
                // 왼쪽 노드가 없으면 새롭게 객체를 생성
                
                else this.left.insert(n);
                // 이미 왼쪽 노드가 존재한다면, 그 왼쪽 노드의 왼쪽에 넣어준다.
            }
            else{
                if(this.right == null) this.right = new Node(n);
                // 오른쪽 노드가 없으면 새롭게 객체를 생성
                
                else this.right.insert(n);
                // 이미 오른쪽 노드가 존재한다면, 그 왼쪽 노드의 왼쪽에 넣어준다.
            }
        }
    }
}
