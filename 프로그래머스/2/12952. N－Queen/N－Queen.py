ans = 0

def solution(n):
    N = n

    v1 = [False]*n # 세로
    v2 = [False]*(2*n) # 왼쪽 대각선
    v3 = [False]*(2*n) # 오른쪽 대각선
    
    def dfs(row):
        global ans
        
        if N == row:
            ''' 
            체스판 마지막 줄에 퀸을 배치할 수 있을 때가 정답 케이스가 된다.
            퀸은 가로, 세로 같은 행과 열에 2개 이상을 배치할 수 없기 때문이다.
            '''
            ans += 1
            return

        for j in range(N):
            # n번째 행에서 0~N번 열을 순회하며 해당 위치에 퀸을 배치할 수 있는지 체크한다.
            if not v1[j] and not v2[row-j] and not v3[row+j]:
                v1[j] = True
                v2[row-j] = True
                v3[row+j] = True

                dfs(row+1) # 다음 행으로 넘어가서 체크

                v1[j] = False
                v2[row-j] = False
                v3[row+j] = False
    
    dfs(0)
    
    return ans