import itertools

n, m = map(int, input().split())
arr = []
for _ in range(n):
    arr.append(list(map(int, input().split())))

house = []
chicken = []
for x in range(n):
    for y in range(n):
        if arr[x][y] == 1:
            # 집이라면
            house.append((x, y))
        elif arr[x][y] == 2:
            chicken.append((x, y))

com = list(itertools.combinations(chicken, m))

result = []
for c in com:
    tmp_r = [2501] * len(house)
    for h in range(len(house)):
        for c_ in c:
            temp = abs(house[h][0] - c_[0]) + abs(house[h][1] - c_[1])
            tmp_r[h] = min(tmp_r[h], temp)
    result.append(sum(tmp_r))

print(min(result))