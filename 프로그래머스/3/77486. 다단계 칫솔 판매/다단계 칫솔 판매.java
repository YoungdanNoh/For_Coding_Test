import java.util.*;

class Solution {
    
    class Node{
        int next; // 내가 돈 줘야하는 노드 번호
        int money; // 내 수익금
        
        public Node(int next, int money){
            this.next = next;
            this.money = money;
        }
    }
    
    static int N;
    static Node[] tree;
    static HashMap<String, Integer> m = new HashMap<>();
    static int[] ans;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        N = enroll.length;
        tree = new Node[N];
        ans = new int[N];
        
        for(int i=0; i<N; i++){
            m.put(enroll[i], i);
        }
        
        for(int i=0; i<N; i++){
            if(referral[i].equals("-")){
                tree[m.get(enroll[i])] = new Node(-1, 0);
                
            }else{
                tree[m.get(enroll[i])] = new Node(m.get(referral[i]), 0);
                
            }
        }
        
        for(int i=0; i<seller.length; i++){
            int money = amount[i] * 100; // 수익금
            
            int give = (int) Math.floor(money*0.1); // 줘야할 돈
            tree[m.get(seller[i])].money += money - give; // 판매자가 가질 돈
            
            int parent = tree[m.get(seller[i])].next;
            
            Node rec;
            
            //System.out.println("mary 수익금: " + tree[1].money);
            
            if(parent != -1){
                rec = tree[parent]; // 내 추천인
                
            }else{
                continue;
                
            }
            
            // 트리의 꼭대기까지 갈 때 동안 반복
            while(true){
                int m = give;
                give = (int) Math.floor(m*0.1); // 줘야할 돈
                
                rec.money += m - give;
                
                if(rec.next == -1 || give == 0) break;
                
                rec = tree[rec.next];
            }
            
        }
        
        for(int i=0; i<N; i++){
            ans[i] = tree[i].money;
        }
        
        return ans;
    }
}