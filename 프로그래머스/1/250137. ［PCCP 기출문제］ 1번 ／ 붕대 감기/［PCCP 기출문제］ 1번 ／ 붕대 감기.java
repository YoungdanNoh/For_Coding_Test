class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int hp = health;
        int combo = 0; // 연속 성공횟수
        int time = 0;
        int endTime = attacks[attacks.length-1][0];
        int idx = 0;
        
        for(int i=1; i<=endTime; i++){
            
            if(attacks[idx][0] == i){
                // 공격할 시간이 됐다면
                hp -= attacks[idx][1];
                idx++;
                combo = 0;
                
                if(hp <= 0){
                    hp = -1;
                    break;
                }
                
                continue;
            }
            
            if(hp == health){
                // 연속 성공 횟수만 up
                combo++;
            }else{
                combo++;
                hp += bandage[1];
                
                if(hp > health){
                    hp = health;
                }
            }
            
            if(combo == bandage[0]){
                // 추가 회복량 획득
                hp += bandage[2];
                
                if(hp > health){
                    hp = health;
                }
                
                combo = 0;
            }
            
        }
        
        return hp;
    }
}