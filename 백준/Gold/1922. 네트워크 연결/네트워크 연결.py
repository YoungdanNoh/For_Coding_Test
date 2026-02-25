import sys

input = sys.stdin.readline

N = int(input())
M = int(input())

parent = [i for i in range(N+1)] # 각 노드의 부모

def find(x):
    if parent[x] == x:
        # 집합의 대표 노드 return
        return x
    else:
        parent[x] = find(parent[x])
        return parent[x]

def union(x, y):
    p1 = find(x)
    p2 = find(y)

    if p1 == p2:
        return

    # 둘의 부모가 다르면 합치기 가능
    if p1 < p2:
        parent[p2] = p1
    else:
        parent[p1] = p2

graph = [] # 간선을 비용순으로 정렬하면 되므로 2차원으로 만들면 됨
for _ in range(M):
    a, b, c = map(int, input().split())
    graph.append((a, b, c))

graph.sort(key = lambda x: x[2]) # 비용순 정렬

ans = 0
for a, b, c in graph:
    if find(a) != find(b):
        ans += c
        union(a, b)

print(ans)