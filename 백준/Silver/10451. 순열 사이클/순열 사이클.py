import sys

sys.setrecursionlimit(2000)

t = int(input())

def dfs(graph, visited, start):
    global cnt
    visited[start] = True
    idx = graph[start] - 1 # 리스트 인덱스는 (노드 번호 - 1)
    if not visited[idx]:
        dfs(graph, visited, idx)

for _ in range(t):
    n = int(input())
    graph = list(map(int, input().split()))

    visited = [False] * n # 방문 정보를 저장한다.

    cnt = 0  # 그래프의 개수를 세는 용도
    for i in range(n):
        if visited[i] == 0:
            dfs(graph, visited, i)
            cnt += 1
    print(cnt)