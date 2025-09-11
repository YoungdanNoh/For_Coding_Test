import java.util.*;

class Solution {
    
    public boolean select(ArrayList<Integer> cards1, ArrayList<Integer> cards2, int check) {
        for (int i = 0; i < cards1.size(); i++) {
            int card = cards1.get(i);
            int need = check - card;
            if (cards2.contains(need)) {
                cards1.remove(i);                         // index로 제거
                cards2.remove(Integer.valueOf(need));     // 값으로 제거
                return true;
            }
        }
        return false;
    }
    
    public int solution(int coin, int[] cards) {
        
        int N = cards.length;
        int S = N/3;
        
        ArrayList<Integer> myCards = new ArrayList<>();
        for (int idx = 0; idx < S; idx++) myCards.add(cards[idx]);

        Deque<Integer> q = new ArrayDeque<>();
        for (int idx = S; idx < N; idx++) q.offer(cards[idx]);

        int turn = 1;
        ArrayList<Integer> picks = new ArrayList<>();

        while (coin >= 0 && q.size() >= 2) {
            // 카드 2장 뽑기
            picks.add(q.poll());
            picks.add(q.poll());

            // 코인을 사용하지 않는 방법 확인 
            if (select(myCards, myCards, N + 1)) {
                turn += 1;
                continue;
            }
            // 코인을 1개만 사용
            else if (coin >= 1 && select(myCards, picks, N + 1)) {
                coin -= 1;
            }
            // 코인을 2개 다 사용
            else if (coin >= 2 && select(picks, picks, N + 1)) {
                coin -= 2;
            }
            // n+1 경우의 수가 없을 경우
            else {
                break;
            }

            turn += 1;
        }

        return turn;
    }
}
