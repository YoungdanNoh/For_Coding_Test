class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int play_time_sec = timeToSec(play_time);
        int adv_time_sec  = timeToSec(adv_time);

        long[] time_to_total = new long[play_time_sec + 1]; 
        
        for (String log : logs) {
            String[] parts = log.split("-");
            int start = timeToSec(parts[0]);
            int end   = timeToSec(parts[1]);

            // 시작: 시청자 증가
            time_to_total[start] += 1;
            // 끝 미포함: end에서 -1
            time_to_total[end] -= 1;
            
        }

        // 1차 누적합 -> 시각별 시청자 수
        for (int i = 1; i < time_to_total.length; i++) {
            time_to_total[i] += time_to_total[i - 1];
        }

        // 2차 누적합 -> 구간합 빠른 계산
        for (int i = 1; i < time_to_total.length; i++) {
            time_to_total[i] += time_to_total[i - 1];
        }

        // 초기 구간 [0, adv_time_sec-1]
        long max_total = time_to_total[adv_time_sec - 1];
        int answer = 0;

        // [s, s+adv_time_sec-1] 합 = prefix[s+adv_time_sec] - prefix[s]
        for (int s = 1; s + adv_time_sec - 1 <= play_time_sec; s++) {
            long total = time_to_total[s + adv_time_sec - 1] - time_to_total[s - 1];
            if (total > max_total) {
                max_total = total;
                answer = s;
            }
        }

        return secToTime(answer);
    }
    
    private int timeToSec(String time) {
        String[] arr = time.split(":");
        int h = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int s = Integer.parseInt(arr[2]);
        return h * 3600 + m * 60 + s;
    }

    private String secToTime(int time) {
        int h = time / 3600; time %= 3600;
        int m = time / 60;   time %= 60;
        int s = time;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}