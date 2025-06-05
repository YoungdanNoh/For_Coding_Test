import sys

N = int(sys.stdin.readline()) # 체스판 크기

v1 = [0 for i in range(N)]
v2 = [0 for i in range(N*2-1)]
v3 = [0 for i in range(N*2-1)]

ans = 0
def dfs(n):
    global ans

    if(n == N):
        ans += 1
        return

    for j in range(N):
        if(v1[j] == v2[n-j] == v3[n+j] == 0):

            v1[j] = v2[n - j] = v3[n + j] = 1
            dfs(n+1)
            v1[j] = v2[n - j] = v3[n + j] = 0

dfs(0)
print(ans)