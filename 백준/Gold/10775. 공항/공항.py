import sys

def find_parent(x):
    if x != parent[x]:
        parent[x] = find_parent(parent[x])
    return parent[x]

def union_parent(x, y):
    x = find_parent(x)
    y = find_parent(y)

    # 현재 게이트 번호 이하에서 가장 큰 빈 게이트를 저장한다
    if x < y:
        parent[y] = x
    else:
        parent[x] = y

read = sys.stdin.readline

G = int(read())
P = int(read())

parent = [i for i in range(G + 1)]
plane = []
for _ in range(P):
    plane.append(int(read()))

ans = 0
for p in plane:
    # p가 도킹할 수 있는 최대 게이트 번호를 찾는다.
    x = find_parent(p)

    if x == 0:
        break

    union_parent(x, x-1)
    ans += 1

print(ans)