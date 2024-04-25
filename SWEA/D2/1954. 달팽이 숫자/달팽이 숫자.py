t = int(input())

# 오, 아, 왼, 위 순서로 방향을 바꿀 것임
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def dfs(arr, direc, x, y, n, cnt):
    tx = x + dx[direc]
    ty = y + dy[direc]

    if cnt > n ** 2:
        return

    if tx >= n or tx < 0 or ty >= n or ty < 0:
        if direc == 3:
            direc = 0
        else:
            direc += 1
        dfs(arr, direc, x, y, n, cnt)
    elif arr[tx][ty] != -1:
        if direc == 3:
            direc = 0
        else:
            direc += 1
        dfs(arr, direc, x, y, n, cnt)
    else:
        arr[tx][ty] = cnt
        dfs(arr, direc, tx, ty, n, cnt + 1)


for _ in range(t):
    direc = 0
    x = 0
    y = 0

    n = int(input())
    arr = [[-1] * n for i in range(n)]
    arr[0][0] = 1

    dfs(arr, direc, x, y, n, 2)
    print("#{0}".format(_+1))
    for i in range(n):
        for j in range(n):
        	print(arr[i][j], end = " ")
        print()