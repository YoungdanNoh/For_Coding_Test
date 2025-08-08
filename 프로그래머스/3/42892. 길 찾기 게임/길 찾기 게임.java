import java.util.*;

class Solution {
    
    class Node{
        int idx;
        int x, y;
        Node left;
        Node right;
        
        public Node(int idx, int x, int y){
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
        
        public void insert(Node child){
            if(this.x < child.x){
                if(this.right == null){
                    this.right = child;
                    
                }else{
                    this.right.insert(child);
                }
                
            }else{
                if(this.left == null){
                    this.left = child;
                    
                }else{
                    this.left.insert(child);
                }
                
            }
            
        }
    }
    
    static ArrayList<Integer> tmp;
    
    public void preorder(Node node){
        if(node == null) return;
        
        tmp.add(node.idx);
        preorder(node.left);
        preorder(node.right);
    }
    
    public void postorder(Node node){
        if(node == null) return;
        
        postorder(node.left);
        postorder(node.right);
        tmp.add(node.idx);
    }
    
    public int[][] solution(int[][] nodeinfo) {
        
        int N = nodeinfo.length;
        int[][] sortinfo = new int[N][3];
        for(int i=0; i<N; i++){
            sortinfo[i][0] = nodeinfo[i][0];
            sortinfo[i][1] = nodeinfo[i][1];
            sortinfo[i][2] = i+1;
        }
        
        Arrays.sort(sortinfo, (a, b) -> {
            if(a[1] == b[1]){
                // 같은 레벨
                return Integer.compare(a[0], b[0]);
            
            }else{
                return Integer.compare(b[1], a[1]);
            }
        });
        
        Node root = new Node(sortinfo[0][2], sortinfo[0][0], sortinfo[0][1]); // root 노드 생성
        for(int i=1; i<sortinfo.length; i++){
            root.insert(new Node(sortinfo[i][2], sortinfo[i][0], sortinfo[i][1])); // 자식들 넣기
            
        }
        
        int[][] ans = new int[2][N];
        
        tmp = new ArrayList<>();
        preorder(root);
        for(int i=0; i<tmp.size(); i++){
            ans[0][i] = tmp.get(i);
        }
        
        tmp = new ArrayList<>();
        postorder(root);
        for(int i=0; i<tmp.size(); i++){
            ans[1][i] = tmp.get(i);
        }
        
        return ans;
    }
}