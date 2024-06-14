N = int(input())

dp = [[0]*11]*(N+1)
for d in range(1, 11):
    dp[1][d] = 1

for i in range(2, N+1):
    for j in range(1, 11):
        dp[i][j] = sum(dp[i-1][j:])

print(sum(dp[N])%10007)