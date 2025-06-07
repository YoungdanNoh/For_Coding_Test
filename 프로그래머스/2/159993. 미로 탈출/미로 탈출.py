from collections import deque

def solution(maps):
    answer = 0

    sx, sy = 0, 0 # 시작 지점
    ex, ey = 0, 0 # 출구
    lx, ly = 0, 0 # 레버 위치
    
    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if(maps[i][j] == "S"):
                sx, sy = i, j
                
            if(maps[i][j] == "E"):
                ex, ey = i, j
                
            if(maps[i][j] == "L"):
                lx, ly = i, j
                
    # 상, 하, 좌, 우
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    def bfs(s_x, s_y, e_x, e_y):
        visited = [[False] * len(maps[0]) for _ in range(len(maps))]
        
        q = deque([(s_x, s_y, 0)]) # x, y, 시간
        visited[s_x][s_y] = True
        
        while q:
            cx, cy, time = q.popleft()
            
            if(cx == e_x and cy == e_y):
                # 레버 or 출구에 도달했는가?
                return time
            
            for i in range(4):
                tx = cx + dx[i]
                ty = cy + dy[i]
                
                if(tx<0 or tx>=len(maps) or ty<0 or ty>=len(maps[0])):
                    continue
                
                if(visited[tx][ty]):
                    continue
                    
                if(maps[tx][ty] == 'X'):
                    continue
                
                visited[tx][ty] = True
                q.append((tx, ty, time+1))
                
        return -1
                
    # 1. 시작점 -> 레버
    tmp = bfs(sx, sy, lx, ly)
    #print(sx, " ", sy, " 레버 ", lx, " ", ly)
    #print("1: ", tmp)
    if(tmp == -1):
        return -1
    else:
        answer += tmp
    
    # 2. 레버 -> 도착점
    tmp = bfs(lx, ly, ex, ey)
    #print("2: ", tmp)
    if(tmp == -1):
        return -1
    else:
        answer += tmp
    
    return answer