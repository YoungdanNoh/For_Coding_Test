import copy
from itertools import combinations
from collections import deque

n = int(input())

graph = []
temp = []
teacher = []
for i in range(n):
    graph.append(list(input().split()))
    for j in range(n):
        if graph[i][j] == "X":
            temp.append((i, j))
        if graph[i][j] == "T":
            teacher.append((i, j))

case = list(combinations(temp, 3))

visited = [[False] * n for _ in range(n)]

# 상하좌우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def dfs(graph):
    for t in teacher:
        for i in range(4):
            tx = t[0]
            ty = t[1]
            while 0 <= tx < n and 0 <= ty < n:
                if graph[tx][ty] == "O":
                    break
                if graph[tx][ty] == "S":
                    return False

                tx += dx[i]
                ty += dy[i]
    return True

flag = False
for c in case:
    tmp = copy.deepcopy(graph)
    answer = True

    for c_ in c:
        tmp[c_[0]][c_[1]] = "O"
    if dfs(tmp):
        print("YES")
        flag = True
        break
if not flag:
    print("NO")