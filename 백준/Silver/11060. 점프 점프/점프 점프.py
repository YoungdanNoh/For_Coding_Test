import sys

N = int(sys.stdin.readline().strip())

arr = list(map(int, sys.stdin.readline().strip().split()))

dp = [0 for i in range(N)]
dp[0] = 1

for i in range(N):
    if(dp[i] > 0 and arr[i] > 0):
        # 해당 위치로 가는 경로가 있고 and 점프 가능한 칸 수가 0개 보다 크다면 이동 가능

        for j in range(i+1, arr[i]+i+1):
            if(j < N):
                # 오른쪽으로 점프할 수 있다면?
                if(dp[j] == 0):
                    # 해당 위치로 가는 경로가 존재하지 않는 경우에만 횟수 추가
                    dp[j] = dp[i] + 1

print(dp[N-1]-1)