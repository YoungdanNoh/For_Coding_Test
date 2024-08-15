import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken()); //방의 개수
        long Hatk = Integer.parseInt(st.nextToken()); //용사의 초기 공격력
        long room, a, h;
        long curHP = 0, maxHP = 0; //현재 체력을 0부터 시작해서 음수가 나오면 max값에 더해주기
        long attack;
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(bf.readLine());
            room = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken()); //몬스터 공격력 or 용사의 공격력 UP
            h = Integer.parseInt(st.nextToken()); //몬스터 생명력 or 용사의 생명력 UP
            
            if(room == 1) {
            	//몬스터가 있는 방
                
            	attack = (long) ((Math.ceil((double)h/Hatk) - 1)*a);
            	//버텨야 하는 공격력
                
            	if(curHP >= attack) {
            		curHP -= attack; //버틸 수 있다면 현재 체력에서 감소시키기
            	}else {
            		maxHP += attack - curHP; //부족한 체력만큼 최대 생명력 증가시키기
            		curHP = 0;
            	}
            }else {
            	//포션이 있는 방
                
            	Hatk += a;
            	curHP = Math.min(curHP+h, maxHP);
            }
        }
        System.out.println(maxHP + 1);

        /*
        * 최대로 피해를 입을 때의 공격력을 용사가 버텨주어야 한다.
        * 1. 몬스터가 있는 방일 때
        * 용사가 버텨야 하는 체력 => 몬스터 공격력 * (몬스터 체력/용사 공격력 - 1)
        * 최종 값은 "용사가 몬스터 공격에서부터 버텨야 하는 체력"에 +1 한 체력이 필요하다.
        * */
    }
}