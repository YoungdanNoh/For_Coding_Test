import sys

N, M = map(int, sys.stdin.readline().split())

arr = []

for i in range(N):
    arr.append(list(map(int, sys.stdin.readline().split())))

dx = [0, -1, -1]
dy = [-1, -1, 0]

dp = [[0]*M for i in range(N)]

for i in range(N):
    for j in range(M):

        max_dp = 0
        for k in range(3):
            # 왼, 위, 대각선 중 어느 곳에서 오는게 좋을까?
            tx = i + dx[k]
            ty = j + dy[k]

            if(tx>=0 and tx<N and ty>=0 and ty<M):
                max_dp = max(max_dp, dp[tx][ty])

        dp[i][j] = max_dp + arr[i][j]

print(dp[N-1][M-1])