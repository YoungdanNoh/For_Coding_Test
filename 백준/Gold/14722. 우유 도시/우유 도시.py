import sys

input = sys.stdin.readline

N = int(input())
city = []

for i in range(N):
    tmp = list(map(int, input().split()))
    city.append(tmp)

dp = [[0]*N for _ in range(N)]
'''
dp 배열 초기화
1. 첫 행은 왼쪽 -> 오른쪽으로 가는 경우만 가능
2. 첫 열은 위 -> 아래로 가는 경우만 가능
'''

# 0 -> 1 -> 2 -> 0 ... 순회
if city[0][0] == 0:
    dp[0][0] = 1

# 첫 행 초기화
for j in range(1, N):
    cnt = dp[0][j-1] # 섭취한 우유 갯수
    if city[0][j] == (cnt%3):
        # 섭취 가능
        dp[0][j] = cnt+1
    else:
        dp[0][j] = cnt

# 첫 열 초기화
for i in range(1, N):
    cnt = dp[i-1][0] # 섭취한 우유 갯수
    if city[i][0] == (cnt%3):
        # 섭취 가능
        dp[i][0] = cnt+1
    else:
        dp[i][0] = cnt

# 나머지 dp값들 초기화
for i in range(1, N):
    for j in range(1, N):
        # 1. 왼쪽 -> 오른쪽
        left = dp[i][j-1]
        if city[i][j] == (left%3):
            left = left + 1

        # 2. 위 -> 아래
        up = dp[i-1][j]
        if city[i][j] == (up%3):
            up = up + 1

        dp[i][j] = max(left, up)

print(dp[N-1][N-1])