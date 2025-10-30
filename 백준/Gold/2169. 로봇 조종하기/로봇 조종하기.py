import sys

read = sys.stdin.readline

N, M = map(int, read().split())

board = []
for _ in range(N):
    tmp = list(map(int, read().split()))
    board.append(tmp)

# 첫 행은 왼쪽에서 오른쪽으로 가는 방법밖에 없음
for i in range(1):
    for j in range(1, M):
        board[i][j] += board[i][j-1]

for i in range(1, N):
    # 행별로 위쪽에서 내려오는 경우
    up = [0 for _ in range(M)]
    for j in range(M):
        up[j] = board[i-1][j]

    # 왼 -> 오 경우들의 가치
    left = [0 for _ in range(M)]
    left[0] = up[0] + board[i][0]
    for j in range(1, M):
        # 왼쪽에서 오는 경우, 위에서 내려오는 경우 중 최대값을 찾기
        left[j] = max(left[j - 1], up[j]) + board[i][j]

    # 오 -> 왼 경우들의 가치
    right = [0 for _ in range(M)]
    right[M-1] = up[M-1] + board[i][M-1]
    for j in range(M - 2, -1, -1):
        # 오른쪽에서 오는 경우, 위에서 내려오는 경우 중 최대값을 찾기
        right[j] = max(right[j + 1], up[j]) + board[i][j]

    # 왼 -> 오, 오 -> 왼 중 최대 가치로 현재 위치를 갱신
    for j in range(M):
        board[i][j] = max(left[j], right[j])

print(board[N-1][M-1])