class Solution {
    public int solution(String[] lines) {
        
        // 시작 시간: 시작시간 - 1초 + 0.001초
        // 종료 시간: 처리가 끝나는 시점(입력값)
        
        int N = lines.length;
        int ans = 0;
        
        for(int i=N-1; i>=0; i--){
            
            int cnt = 1; // 일단 나(자기 자신)는 초당 최대 처리량에 포함됨
            
            int[] time = startToEnd(lines[i]); // 시작 시간, 종료 시간
            
            // 종료를 기점으로 1초 구간 보기
            int winStart = time[1];
            int winEnd = time[1] + 1000 - 1;
            
            for(int j=N-1; j>=0; j--){
                if(i == j) continue;
                
                int[] tmp = startToEnd(lines[j]);
                
                if(winStart <= tmp[1] && winEnd >= tmp[0]) {
                    cnt++;
                }
            }
            
            ans = Math.max(ans, cnt);
            
        }
        System.out.println(ans);
        
        return ans;
    }
    
    public int[] startToEnd(String line){
        String[] log = line.split(" ");
        String[] time = log[1].split(":");
            
        int endTime = 0; // 종료 시간
        endTime += Integer.parseInt(time[0]) * 60 * 60 * 1000;
        endTime += Integer.parseInt(time[1]) * 60 * 1000;
        endTime += (int)Math.round(Double.parseDouble(time[2]) * 1000);
        // 시간*60*60*1000 = 밀리세컨드 단위
        // 분*60*1000 = 밀리세컨드 단위
        // 초*1000 = 밀리세컨드 단위
            
        String[] T = log[2].split("s");
        int t = (int)Math.round(Double.parseDouble(T[0]) * 1000);
        int startTime = Math.max(0, (endTime - t + 1));// 시작 시간
        //System.out.println(startTime + ", " + endTime);
        
        return new int[]{startTime, endTime};
    }
}