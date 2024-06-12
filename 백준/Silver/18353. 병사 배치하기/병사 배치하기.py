n = int(input())
power = list(map(int, input().split()))

dp = [1]*n

for i in range(1, n):
    for j in range(i):
        if power[j] > power[i]:
            dp[i] = max(dp[j]+1, dp[i])
            # 현재 병사를 포함한 것 vs 현재 병사부터 시작하는 것

print(n - max(dp))