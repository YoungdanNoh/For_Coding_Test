import sys

sys.setrecursionlimit(10**6)
ans = 'z'

def solution(n, m, x, y, r, c, k):
    
    # d, l, r, u
    d = [('d', 1, 0),
        ('l', 0, -1),
        ('r', 0, 1),
        ('u', -1, 0)]
    
    # 현재 x, y 위치, 경로 알파벳, 이동 횟수
    def dfs(tx, ty, route, cnt):
        global ans
        
        if tx == r and ty == c and cnt == k:
            # 도착
            if ans > route:
                ans = route
            return
        
        if cnt >= k:
            # 이동 횟수 초과
            return
        
        remain = k - cnt # 남은 이동 횟수
        distance = abs(tx - r) + abs(ty - c) # 목적지까지 최단 거리
        if remain < distance:
            # 남은 이동 횟수로 r, c에 도착할 수 없음
            return
        if (remain - distance) % 2 == 1:
            # 최단거리보다 더 움직여야 하는 횟수가 홀수라면, 최종적으로 목적지에 맞춰 도착할 수 없다
            return
        
        for al, dx, dy in d:
            ntx = tx + dx
            nty = ty + dy
            
            if ntx < 1 or ntx > n or nty < 1 or nty > m:
                # 격자를 벗어남
                continue
            if (route+al) > ans:
                # 현재 정답보다 더 나은 결과를 만들 수 없음
                continue
            dfs(ntx, nty, route+al, cnt+1)
    
    dfs(x, y, '', 0)
    
    if ans == 'z':
        return 'impossible'
    else:
        return ans