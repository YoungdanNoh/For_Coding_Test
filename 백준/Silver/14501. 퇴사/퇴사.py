n = int(input())
t = [0]*n
p = [0]*n

for _ in range(n):
    t[_], p[_] = map(int, input().split())

dp = [0]*(n+1)

for i in range(n-1, -1, -1):
    if i + t[i] > n:
        # 상담 불가능
        dp[i] = dp[i+1] # 이전의 최대 이익을 가져온다
    else:
        dp[i] += max(dp[i+1], dp[i+t[i]] + p[i])

print(max(dp))