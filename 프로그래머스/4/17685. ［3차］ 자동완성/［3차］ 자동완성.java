import java.util.*;

class Node{
    HashMap<Character, Node> child;
    int cnt;
    boolean endOfWord;
    
    public Node(){
        this.child = new HashMap<>();
        this.cnt = 0;
        this.endOfWord = false;
    }
}

class Trie{
    // 모든 문자열 삽입, 탐색, 삭제를 시작하는 루트 노드
    
    Node root;
    
    public Trie(){
        this.root = new Node();
    }
    
    public void insert(String str){
        // 문자열 삽입
        Node node = this.root; // 시작노드는 root
        
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            
            node.child.putIfAbsent(c, new Node()); // c라는 문자가 자식에 없다면 추가
            node.child.get(c).cnt += 1; // 해당 문자 등장 수 + 1
            
            node = node.child.get(c); // 자식 node로 이동 (현재 삽입한 문자열의 위치로 이동)
        }
        
        node.endOfWord = true; // 마지막 글자임을 표시
        
    }
    
    public int search(String str){
        // 문자열 탐색
        Node node = this.root; // 시작노드는 root
        
        boolean flag = true;
        
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            
            if(node.child.containsKey(c)){
                // c라는 문자를 자식으로 가지고 있다면
                node = node.child.get(c); // 해당 자식으로 이동하여 더 탐색
                
                if(node.cnt == 1){
                    // 해당 문자까지만 입력하면 찾을 수 있음
                    return i+1;
                }
                
            }else{
                flag = false;
                
            }
        }
        
        if(flag){
            return str.length(); // 해당 문자열을 전부 다 입력해야지만 탐색 가능
            
        }else{
            return -1; // 해당 문자열을 찾을 수 없음
            
        }
        
    }
    
    public boolean delete(String str){
        boolean result = delete(this.root, str, 0);
        return result;
    }
    
    public boolean delete(Node node, String str, int idx){
        char c = str.charAt(idx); // 현재 탐색 중인 문자
        
        if(!node.child.containsKey(c)){
            // c라는 문자를 현재 node가 자식으로 가지고 있지 않다면 삭제 불가능
            return false;
        }
        
        Node cur = node.child.get(c);
        idx++;
        
        if(idx == str.length()){
            // 문자열의 끝에 도달
            if(!cur.endOfWord){
                // 해당 문자가 문자열의 끝이 아니라면 삭제 불가(해당 단어 존재하지 않음)
                return false;
            }
            
            // 삭제 가능
            // endOfWord를 false로 변경
            cur.endOfWord = false;
            
            if(cur.child.isEmpty()){
                // 해당 단어로부터 뻗어나가는 단어가 더 이상 없다면
                // 해당 문자(c)의 부모 노드에서 (c) 삭제
                node.child.remove(c);
            }
            
        }else{
            // 문자열의 끝에 도달하지 않았을 땐
            // 다시 한번 재귀 호출
            if(!this.delete(cur, str, idx)){
                // 삭제 실패
                return false;
            }
            
            if(!cur.endOfWord && cur.child.isEmpty()){
                // 현재 문자가 다른 문자열의 끝 문자가 아님과 동시에
                // 현재 문자의 자식이 없다면 해당 문자를 부모에서 삭제
                node.child.remove(c);
            }
            
        }
        
        return true;
    }
}

class Solution {
    public int solution(String[] words) {
        
        Trie root = new Trie();
        
        for(int i=0; i<words.length; i++){
            root.insert(words[i]);
        }
        
        int ans = 0;
        
        for(int i=0; i<words.length; i++){
            ans += root.search(words[i]);
        }
        
        return ans;
    }
}