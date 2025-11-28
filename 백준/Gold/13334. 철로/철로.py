import sys
import heapq

input = sys.stdin.readline

N = int(input())
people = []

for _ in range(N):
    tmp = list(map(int, input().strip().split()))
    tmp.sort()
    people.append(tmp)

people.sort(key = lambda x: x[1]) # 끝 위치를 기준으로 정렬
D = int(input())

pq = []
answer = 0
for s, e in people:
    # e는 임의의 선로 끝 점
    heapq.heappush(pq, s)

    while len(pq) > 0 and pq[0] < (e-D):
        # D길이의 선로로 커버되지 않는다면 pop
        heapq.heappop(pq)

    # e를 끝점으로 하는 D길이의 선로가 커버할 수 있는 사람들 수의 최대값을 구한다
    answer = max(answer, len(pq))

print(answer)