import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        
        // n이 코어 개수보다 작거나 같으면 바로 n번째 코어에 배정
        if (n <= cores.length) return n;

        // 0time에 cores.length개 작업은 바로 배정되므로 제외
        n -= cores.length;

        long left = 1;
        // cores 배열 안의 최대값을 구해 n을 곱함
        long right = (long) Arrays.stream(cores).max().getAsInt() * n;

        // 이분 탐색
        while (left <= right) {
            long mid = (left + right) / 2;
            long capacity = 0;
            for (int c : cores) {
                capacity += mid / c;
            }

            if (capacity >= n) {
                right = mid - 1;  // 더 왼쪽에도 답이 있을 수 있음
            } else {
                left = mid + 1;   // 아직 부족하니 오른쪽으로 이동
            }
        }

        // n번째 작업이 완료되기 직전 시간까지 완료된 작업 수
        long doneBefore = 0;
        for (int c : cores) {
            doneBefore += right / c;
        }

        long remain = n - doneBefore; // t 시각에 끝난 작업 중 몇 번째가 n번째인가?

        // t 시각에 동시에 끝나는 코어들 순회
        for (int i = 0; i < cores.length; i++) {
            if ((right+1) % cores[i] == 0) {
                remain--;
                if (remain == 0) {
                    return i + 1; // 코어 번호는 1-based
                }
            }
        }

        return -1; // 논리상 도달 불가
        
    }
}