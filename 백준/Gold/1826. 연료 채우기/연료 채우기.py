import sys, heapq

N = int(sys.stdin.readline().strip())

gasStation = [0 for i in range(1000001)]
for i in range(N):
    a, b = map(int, sys.stdin.readline().split())
    gasStation[a] = b

L, P = map(int, sys.stdin.readline().split())

q = []
result = 0
for i in range(1, L+1):
    if(gasStation[i] != 0):
        # 가는 길에 주유소가 있다면 우선순위 큐에 삽입
        heapq.heappush(q, -gasStation[i])

    P -= 1 # 연료량 감소

    if(i == L):
        # 목적지 도착
        break

    if(P == 0):
        # 연료가 없다면 제일 큰 값을 큐에서 빼와서 더해주기
        # 즉, 해당 주유소에서 주유

        if(len(q) <= 0):
            # 큐에 데이터가 없으면(즉, 주유가능한 주유소가 없다면)
            result = -1
            break

        P += -heapq.heappop(q)
        result += 1

print(result)