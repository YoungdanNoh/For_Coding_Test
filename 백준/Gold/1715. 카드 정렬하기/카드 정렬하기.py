import heapq

n = int(input())

heap = []
for _ in range(n):
    heapq.heappush(heap, int(input()))

result = 0
for i in range(n-1):
    first = heapq.heappop(heap)
    two = heapq.heappop(heap)
    result += first + two

    heapq.heappush(heap, (first + two))

print(result)