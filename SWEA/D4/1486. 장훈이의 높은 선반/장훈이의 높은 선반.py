t = int(input())

def dfs(n, sm):
    global ans
    
    if n == N:
        if B <= sm:
            ans = min(ans, (sm-B))
        return

    dfs(n+1, sm+H[n])
    dfs(n+1, sm)

for tc in range(1, t+1):
    N, B = map(int, input().split())
    H = list(map(int, input().split()))
    # 높이가 B이상인 탑을 만들어야 함

    ans = 10000*20
    dfs(0, 0)

    print("#{0} {1}".format(tc, ans))