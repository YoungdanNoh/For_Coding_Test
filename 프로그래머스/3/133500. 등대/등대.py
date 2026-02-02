import sys
sys.setrecursionlimit(10**6)

def solution(n, lighthouse):
    
    graph = [[] for _ in range(n + 1)]
    for a, b in lighthouse:
        graph[a].append(b)
        graph[b].append(a)
        
    global ans
    ans = 0
    
    def dfs(cur, parent):
        global ans
        
        # 1. cur이 리프 노드인 경우
        # 연결된 노드가 부모 한 개뿐이면 리프임
        if len(graph[cur]) == 1 and graph[cur][0] == parent:
            # 리프는 키지 않고 부모가 키는 것이 더 최적값(부모는 여러 자식을 가질 수 있기 때문)
            
            # cur은 키지 않겠다
            return 1
        
        # 2. cur이 리프가 아닌 경우: 자식 노드들의 dfs 결과를 수집한다
        tmp = 0
        for nxt in graph[cur]:
            if nxt == parent:
                # 부모로 되돌아 가는 것을 방지
                continue
            tmp += dfs(nxt, cur)
            
        # 3. 현재 노드를 켤지 말지 결정
        # 자식들이 모두 0을 반환했다면(= 모두 자식들이 켜져 있음)
        # 현재 노드는 안 켜도 됨
        if tmp == 0:
            # 부모는 키지 않음
            return 1
        
        # 자식 중 하나라도 1을 반환했다면
        # 그 자식과의 간선을 커버하기 위해 현재 노드를 켜야 함
        ans += 1  # 현재 등대 켬
        return 0
    
    dfs(1, 0)
    
    return ans