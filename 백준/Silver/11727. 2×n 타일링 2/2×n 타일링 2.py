N = int(input())

dp = [0]*(N+1) # 인덱스 0은 사용하지 않는다
dp[1] = 1 # 2*1 짜리를 만드는 경우의 수

if N!=1:
    dp[2] = 3 # 2*2 짜리를 만드는 경우의 수

for i in range(3, N+1):
    dp[i] = dp[i-1] + dp[i-2]*2

print(dp[N]%10007)