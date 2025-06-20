import sys

sys.setrecursionlimit(10000)

route = "z"

def solution(n, m, x, y, r, c, k):
    
    dx = [1, 0, 0, -1] # 상하좌우
    dy = [0, -1, 1, 0]
    dAlpha = ['d', 'l', 'r', 'u']
    
    def dfs(cx, cy, cr, ck):
        global route
        
        if(ck == k and cx == (r-1) and cy == (c-1)):
            route = cr
            return
        
        if(ck >= k):
            return
        
        # 현재 위치에서 도착지점까지 k번 움직여서 갈 수 없다면 return
        tmp = abs(cx - (r-1)) + abs(cy - (c-1))
        if (k-ck-tmp)%2 == 1:
            return
        
        if k < (ck + tmp):
            return
        
        for i in range(4):
            tx = cx + dx[i]
            ty = cy + dy[i]
            
            if (tx>=0 and tx<n and ty>=0 and ty<m) and cr < route:
                dfs(tx, ty, cr+dAlpha[i], ck+1)
                    
    dfs(x-1, y-1, "", 0)
    
    #print(route)
    if(route == "z"):
        return "impossible"
    
    else:
        return route