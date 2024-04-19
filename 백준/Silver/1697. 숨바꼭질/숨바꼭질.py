from collections import deque

n, k = map(int, input().split())

visited = [0]*(10**5 + 1)
MAX = 10**5

def bfs(visited, start):
    q = deque()
    q.append(start)

    while q:
        x = q.popleft()

        if k == x:
            return visited[x]

        for i in (x-1, x+1, x*2):
            if i >= 0 and i <= MAX and not visited[i]:
                visited[i] = visited[x] + 1
                q.append(i)

print(bfs(visited, n))