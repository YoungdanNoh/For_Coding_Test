import java.util.*;

class Node{
    Map<Character, Node> child;
    Map<Integer, Integer> remainCnt; // 현재 노드에서 뒤에 남은 글자 수, 해당 글자수인 단어 갯수
    boolean isEnd;
    
    public Node(){
        this.child = new HashMap<>();
        remainCnt = new HashMap<>();
        isEnd = false;
    }
    
}

class Trie{
    Node root;
    
    public Trie(){
        this.root = new Node();
    }
    
    private void bump(Node node, int remain) {
        node.remainCnt.put(remain, node.remainCnt.getOrDefault(remain, 0) + 1);
    }
    
    public void insert(String str){
        Node node = this.root;
        
        bump(node, str.length()); // 루트에 "str길이의 단어 하나 있음"
        
        for(int i=0; i<str.length(); i++){
            
            char c = str.charAt(i);
            
            if(node.child.get(c) == null){
                node.child.put(c, new Node()); // 새로운 노드로 삽입
                node = node.child.get(c); // 자식 노드로 이동
                
            }else{
                node = node.child.get(c); // 자식 노드로 이동
            }
            
            bump(node, str.length() - (i + 1)); // 이 노드에서 남은 길이 기록
        }
        
        
        node.isEnd = true;
    }
    
    public int searchEnd(String str){
        Node node = this.root;
        
        int cnt = 0;
        
        for(int i=0; i<str.length(); i++){
            
            char c = str.charAt(i);
            
            if(c == '?'){
                
                int remain = str.length() - i; // 아직 남은 길이
                return node.remainCnt.getOrDefault(remain, 0);
            }
            
            if(node.child.get(c) == null){
                return 0;
                
            }else{
                node = node.child.get(c); // 자식 노드로 이동
            }
        }
        
        return node.remainCnt.getOrDefault(0, 0);
    }
    
}

class Solution {
    public int[] solution(String[] words, String[] queries) {
        
        Trie trie = new Trie();
        Trie reverseTrie = new Trie();
        
        int[] ans = new int[queries.length];
        
        for(int i=0; i<words.length; i++){
            trie.insert(words[i]);
            
            String rw = new StringBuilder(words[i]).reverse().toString();
            reverseTrie.insert(rw);
            
        }
        
        for(int i=0; i<queries.length; i++){
            
            if(queries[i].charAt(0) != '?'){
                // 접미사에 "?"
                ans[i] = trie.searchEnd(queries[i]);
            
            }else{
                // 접두사에 "?"
                String rw = new StringBuilder(queries[i]).reverse().toString();
                ans[i] = reverseTrie.searchEnd(rw);
                
            }
            
        }
        
        return ans;
    }
}