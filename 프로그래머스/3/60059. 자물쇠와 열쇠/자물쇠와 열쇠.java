class Solution {
    static int N;
    static int M;
    
    public boolean solution(int[][] key, int[][] lock) {
        
        N = lock.length;
        M = key.length;
        
        int[][] key_90 = rotate(key); // 90도 회전
        int[][] key_180 = rotate(key_90); // 180도 회전
        int[][] key_270 = rotate(key_180); // 270도 회전
        
        int[][] new_lock = new int[lock.length*3][lock[0].length*3];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                new_lock[i + N][j + N] = lock[i][j];
            }
        }
        
        int[][][] keys = {key, key_90, key_180, key_270};
        
        for (int[][] k : keys) {
            for (int x = 0; x < 2 * N; x++) {
                for (int y = 0; y < 2 * N; y++) {

                    if (isUnlocked(new_lock, k, x, y)) {
                        return true;
                    }

                }
            }
        }

        return false;
    }
    
    public int[][] rotate(int[][] key){
        int[][] new_key = new int[M][M];
        
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                new_key[i][j] = key[M-1-j][i];
            }
        }
        
        return new_key;
    }
    
    // 열쇠 + 키 값이 모두 1인지 확인
    private boolean isUnlocked(int[][] new_lock, int[][] k, int ox, int oy) {
        
        for (int i = N; i < 2 * N; i++) {
            for (int j = N; j < 2 * N; j++) {
                int v = new_lock[i][j];

                // (i, j)가 키와 겹치면 해당 키 값을 더한다
                int ki = i - ox; // 키 좌표로 변환
                int kj = j - oy;
                if (0 <= ki && ki < M && 0 <= kj && kj < M) {
                    v += k[ki][kj];
                }

                if (v != 1) return false; // 0 or 2면 실패
            }
        }
        
        return true;
    }
}