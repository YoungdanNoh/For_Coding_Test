import copy
from collections import deque

# 시계방향 회전
def rotate(src):
    n = len(src)
    dst = [[0] * n for _ in range(n)]
    for row in range(n):
        for col in range(n):
            dst[row][col] = src[n-col-1][row]
    return dst

def BFS(x, y, num, graph):
    n = len(graph)          # 정사각 격자모양 가로세로 길이
    dx = [-1, 1, 0, 0]      # 상하좌우순
    dy = [0, 0, -1, 1]      # 상하좌우순
    
    graph[x][y] = 2                 # 시작지점 방문처리, 2
    result = [(0, 0)]               # 시작지점을 (0, 0) 위치를 기준으로 평행이동
    queue = deque([(x, y, 0, 0)])   # 시작좌표 (x, y, (0, 0) 기준 x, (0, 0) 기준 y)
    while queue:
        x, y, zx, zy = queue.popleft()  # 현재좌표 (x, y, (0, 0) 기준 x, (0, 0) 기준 y)
        for i in range(4):
            nx = x + dx[i]          # 다음좌표x
            ny = y + dy[i]          # 다음좌표y
            nzx = zx + dx[i]        # (0, 0) 기준 다음좌표x
            nzy = zy + dy[i]        # (0, 0) 기준 다음좌표y
            # Board or Table의 범위이면서, 방문가능한 경우(Board: num = 0, Table: num = 1)
            if 0 <= nx < n and 0 <= ny < n and graph[nx][ny] == num:
                graph[nx][ny] = 2                   # 다음좌표 방문처리
                result.append((nzx, nzy))           # (0, 0) 기준 다음좌표 result에 추가
                queue.append((nx, ny, nzx, nzy))    # 다음좌표 (x, y, (0, 0) 기준 x, (0, 0) 기준 y)
    return result   # (0, 0) 기준, 현재 채워야하는 'Board 빈 칸 덩어리' or 'Table 블록 덩어리' return

def solution(game_board, table):
    # 게임보드 빈 칸 덩어리 좌표 탐색
    blank = []
    n = len(game_board)                     # 정사각 격자모양 -> 가로길이 == 세로길이
    for row in range(n):                                    # Board 각 Row별
        for col in range(n):                                # Board 각 Col에 대해
            if game_board[row][col] == 0:                   # 현재좌표가 빈 칸이라면
                blank.append(BFS(row, col, 0, game_board))  # 연속된 빈 칸 덩어리 좌표탐색
    
    # 테이블 블록 덩어리 좌표 탐색 및 게임보드 빈 칸 채우기
    answer = 0
    for _ in range(4):
        table = rotate(table)                               # 회전된 원본
        copy_table = copy.deepcopy(table)                   # 원본손상 방지를 위한 복사본
        for row in range(n):                                # Table 각 Row별
            for col in range(n):                            # Table 각 Col에 대해
                if copy_table[row][col] == 1:               # 현재좌표가 블록이 있다면
                    block = BFS(row, col, 1, copy_table)    # 연속된 블록 덩어리 좌표탐색
                    if block in blank:                      # 현재 블록 덩어리가 들어갈 빈 칸 덩어리가 있다면
                        answer += len(block)                # 현재블록 격자개수만큼 증가
                        blank.remove(block)                 # 빈 칸 후보에서 현재 빈 칸 삭제
                        table = copy.deepcopy(copy_table)   # 방문처리로 변형된 Table 복사본을 원본에 덮어쓰기
                    else:                                   # 현재 블록 덩어리가 들어갈 빈 칸 덩어리가 없다면
                        copy_table = copy.deepcopy(table)   # 원본을 방문처리로 변형된 Table 복사본에 덮어쓰기
    return answer