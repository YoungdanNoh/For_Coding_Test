import heapq

def solution(land, height):
    ans = 0
    d = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    n = len(land)
    
    # (비용, x, y)
    pq = []
    heapq.heappush(pq, (0, 0, 0)) # (0, 0)에서 부터 탐색 시작
    visited = [[False]*n for _ in range(n)]
    
    while pq:
        cost, x, y = heapq.heappop(pq)
        
        if visited[x][y]:
            continue
        
        visited[x][y] = True
        ans += cost
        
        for dx, dy in d:
            tx = x + dx
            ty = y + dy
            
            if tx<0 or tx>=n or ty<0 or ty>=n:
                continue
            if visited[tx][ty]:
                continue
            
            tc = abs(land[x][y] - land[tx][ty])
            if tc <= height:
                heapq.heappush(pq, (0, tx, ty))
            else:
                heapq.heappush(pq, (tc, tx, ty))
    
    return ans