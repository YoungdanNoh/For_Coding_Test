import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        int N = rc[0].length;
        int M = rc.length;
        
        // ArrayList<Integer> top = new ArrayList<>(); //배열의 첫 행
        // ArrayList<Integer> bottom = new ArrayList<>(); //배열의 마지막 행
        Deque<Integer> left = new ArrayDeque<>(); // 배열의 왼쪽 끝 행
        Deque<Integer> right = new ArrayDeque<>(); // 배열의 오른쪽 끝
        Deque<Deque<Integer>> middle = new ArrayDeque<>();
        // 위 아래 끝 행 제외, 왼쪽 끝 오른쪽 끝을 제외한 요소들 담기
        
        // for(int i=1; i<N-1; i++){
        //     top.add(rc[0][i]);
        //     bottom.add(rc[M-1][i]);
        // }
        for(int i=0; i<M; i++){
            left.add(rc[i][0]);
            right.add(rc[i][N-1]);
            
            Deque<Integer> mid = new ArrayDeque<>();
            for (int j = 1; j < N-1; j++) mid.addLast(rc[i][j]);
            middle.addLast(mid);
        }
        
        // for(int i=0; i<N-2; i++){
        //     System.out.print(top.get(i) +" ");
        // }
        // System.out.println();
        // for(int i=0; i<N-2; i++){
        //     System.out.print(bottom.get(i) +" ");
        // }
        // System.out.println();
        // for(int i=0; i<M; i++){
        //     System.out.print(left.get(i) +" ");
        // }
        // System.out.println();
        // for(int i=0; i<M; i++){
        //     System.out.print(right.get(i) +" ");
        // }
        // System.out.println();
        // System.out.println();
        // for(int i=0; i<M; i++){
        //     for(int j=0; j<middle[i].size(); j++){
        //         System.out.print(middle[i].get(j) +" ");
        //     }
        //     System.out.println();
        // }
        
        
        for(int i=0; i<operations.length; i++){
            if(operations[i].equals("Rotate")){
                // 회전
                // 상단 middle, 하단 middle
                Deque<Integer> topMid = middle.peekFirst();
                Deque<Integer> bottomMid = middle.peekLast();
                
                // 1) left 위 -> topMid 왼쪽
                topMid.addFirst(left.removeFirst());
                // 2) topMid 오른쪽 -> right 위
                right.addFirst(topMid.removeLast());
                // 3) right 아래 -> bottomMid 오른쪽
                bottomMid.addLast(right.removeLast());
                // 4) bottomMid 왼쪽 -> left 아래
                left.addLast(bottomMid.removeFirst());
                
            }else{
                // 한 칸 밀기
                // left, right 원소들 하나씩 아래로 밀기
                left.addFirst(left.removeLast());
                right.addFirst(right.removeLast());
                
                // middle 원소들 하나씩 아래로 밀기
                middle.addFirst(middle.removeLast());
                
            }
        }
        
        // 결과 조립
        int[][] answer = new int[M][N];
        Iterator<Deque<Integer>> it = middle.iterator();
        for (int r = 0; r < M; r++) {
            answer[r][0] = left.removeFirst();
            Deque<Integer> mid = it.next();
            int c = 1;
            for (int v : mid) answer[r][c++] = v;
            answer[r][N - 1] = right.removeFirst();
        }
        return answer;
    }
}