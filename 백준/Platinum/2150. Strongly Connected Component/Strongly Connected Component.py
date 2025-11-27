import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

V, E = map(int, input().split())

# 정방향, 역방향 그래프
g = [[] for _ in range(V + 1)]
gr = [[] for _ in range(V + 1)]

for _ in range(E):
    a, b = map(int, input().split())
    g[a].append(b)   # 정방향
    gr[b].append(a)  # 역방향

visited = [False] * (V + 1)
stack = []  # finish time 순서를 담을 스택

def dfs1(u):
    visited[u] = True
    for v in g[u]:
        if not visited[v]:
            dfs1(v)
    stack.append(u)

# 정방향 DFS로 finish time 스택 쌓기
for i in range(1, V + 1):
    if not visited[i]:
        dfs1(i)

# 2단계: 역방향 DFS로 SCC 찾기
visited = [False] * (V + 1)
sccs = []

def dfs2(u, comp):
    visited[u] = True
    comp.append(u)
    for v in gr[u]:
        if not visited[v]:
            dfs2(v, comp)

while stack:
    u = stack.pop()
    if not visited[u]:
        comp = []
        dfs2(u, comp)
        comp.sort()          # 각 SCC 안의 정점 오름차순
        sccs.append(comp)

# SCC들을 각 그룹의 가장 작은 정점 기준으로 정렬
sccs.sort(key=lambda comp: comp[0])

print(len(sccs))
for comp in sccs:
    print(*comp, -1)