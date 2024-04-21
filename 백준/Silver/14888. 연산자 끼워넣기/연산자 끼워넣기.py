n = int(input())
arr_a = list(map(int, input().split()))

# 순서대로 +, -, x, / 의 개수
op = list(map(int,input().split()))

max_ = -1e10
min_ = 1e10
def dfs(cnt, add, minus, mul, div, result):
    global max_, min_
    if cnt == n:
        max_ = max(max_, result)
        min_ = min(min_, result)
        return
    if add:
        dfs(cnt+1, add-1, minus, mul, div, result + arr_a[cnt])
    if minus:
        dfs(cnt+1, add, minus-1, mul, div, result - arr_a[cnt])
    if mul:
        dfs(cnt+1, add, minus, mul-1, div, result * arr_a[cnt])
    if div:
        if result < 0 and arr_a[cnt] > 0:
            dfs(cnt + 1, add, minus, mul, div - 1, -((-result) // arr_a[cnt]))
        else:
            dfs(cnt+1, add, minus, mul, div-1, result // arr_a[cnt])

dfs(1, op[0], op[1], op[2], op[3], arr_a[0])
print(max_)
print(min_)