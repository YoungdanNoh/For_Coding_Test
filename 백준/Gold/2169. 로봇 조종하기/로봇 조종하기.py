n, m = map(int, input().split())

dp = []
for _ in range(n):
    dp.append(list(map(int, input().split())))

for i in range(1, m):
    # 1행에 관해서는 오른쪽으로만 이동 가능
    dp[0][i] += dp[0][i-1]

for x in range(1, n):
    right = dp[x][:]
    left = dp[x][:]

    for y in range(m):
        # 오른쪽으로 이동할 때
        if y == 0:
            # 1열 이라면 위에서 내려오는 경우밖에 없음
            right[y] += dp[x-1][y]
        else:
            # 위에서 내려오는 경우 and 왼쪽 값을 더하는 경우 중 큰 값을 더한다
            right[y] += max(dp[x-1][y], right[y-1])

    for y in range(m-1, -1, -1):
        # 왼쪽으로 이동할 때
        if y == m-1:
            # 가장 마지막 열이라면 위에서 내려오는 경우밖에 없다.
            left[y] += dp[x-1][y]
        else:
            # 위에서 내려오는 경우 and 오른쪽 값을 더하는 경우 중 큰 값을 더한다
            left[y] += max(dp[x-1][y], left[y+1])

    for y in range(m):
        dp[x][y] = max(left[y], right[y])

print(dp[n-1][m-1])