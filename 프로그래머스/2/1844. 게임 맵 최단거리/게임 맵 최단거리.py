from collections import deque

def solution(maps):
    # 1은 이동이 가능
    lx = len(maps)
    ly = len(maps[0])
    
    # 동서남북
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    
    def bfs(maps, x, y):
        queue = deque()
        queue.append((x, y))
        
        while queue:
            x, y = queue.popleft()
            
            for i in range(4):
                # 4방향으로 모두 이동해본다.
                nx = x + dx[i]
                ny = y + dy[i]
                
                if nx < 0 or nx >= lx or ny < 0 or ny >= ly:
                    continue
                if maps[nx][ny] == 0:
                    continue
                if maps[nx][ny] == 1:
                    maps[nx][ny] = maps[x][y] + 1
                    queue.append((nx, ny))  
                    
    bfs(maps, 0, 0)

    if maps[lx-1][ly-1] == 1:
        return -1
    else:
        return maps[lx-1][ly-1]