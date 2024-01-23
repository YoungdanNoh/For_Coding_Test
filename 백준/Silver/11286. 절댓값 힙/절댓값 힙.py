import heapq, sys

N = int(sys.stdin.readline())

heap = []

for i in range(N):
    temp = int(sys.stdin.readline())
    if temp:
        heapq.heappush(heap, (abs(temp), temp))
    else:
        if heap:
            print(heapq.heappop(heap)[1])
        else:
            print(0)