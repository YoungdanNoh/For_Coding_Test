from collections import deque

def solution(n, wires):
    answer = n
    
    graph = [[] for _ in range(n+1)]
    for a, b in wires:
        graph[a].append(b)
        graph[b].append(a)
    
    for a, b in wires:
        # (a, b) 연결을 끊음 -> a와 연결된 송전탑 갯수를 센다
        visited = [False] * (n+1)
        visited[a] = True
        
        # bfs 탐색
        q = deque()
        q.append(a)
        cnt = 0 # a쪽 송전탑 갯수
        
        while q:
            node = q.popleft()
            cnt += 1
            
            for nxt in graph[node]:
                # node와 연결된 전력망
                if (node == a and nxt == b) or (node == b and nxt == a):
                    # 끊은 전력망은 못 지나감
                    continue
                    
                if not visited[nxt]:
                    visited[nxt] = True
                    q.append(nxt)
                    
        # a쪽은 cnt개, b쪽은 (n - cnt)개
        other = n - cnt
        answer = min(answer, abs(cnt - other))
        
    return answer