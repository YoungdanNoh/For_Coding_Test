import copy
from itertools import combinations
from collections import deque

n, m = map(int, input().split())
graph = []

for _ in range(n):
    graph.append(list(map(int, input().split())))

empty = []
virus = []

for i in range(n):
    for j in range(m):
        if graph[i][j] == 0:
            empty.append((i, j))
        if graph[i][j] == 2:
            virus.append((i, j))

com = list(combinations(empty, 3)) # 벽 세우기가 가능한 영역들의 조합

# 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(temp, x, y, area):
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()
        for d in range(4):
            tx = x + dx[d]
            ty = y + dy[d]

            if tx >= n or tx < 0 or ty >= m or ty < 0:
                continue
            if temp[tx][ty] == 1:
                continue
            if temp[tx][ty] == 2:
                continue
            if temp[tx][ty] == 0:
                temp[tx][ty] = 2  # 바이러스 퍼짐
                area -= 1
                q.append((tx, ty))
    return area

result = 0
for c in com:
    temp = copy.deepcopy(graph)
    area = len(empty) - 3 # 안전 영역의 크기
    a_area = []
    for i in c:
        temp[i[0]][i[1]] = 1 # 벽 세우기

    for v in virus:
        x = v[0]
        y = v[1]
        area = bfs(temp, x, y, area)
    result = max(result, area)
print(result)