def solution(info, edges):
    ans = [] # 정답이 될 수 있는 것들의 집합
    
    visited = [False]*(len(info)) # 방문처리
    visited[0] = True # 0번 노드부터 시작
    
    def dfs(sheep, wolf):
        if sheep <= wolf:
            return
        else:
            ans.append(sheep)
            
        for p, c in edges:
            # 부모 방문, 자식 미방문인 경우 -> 자식 노드를 방문하거나 안 하거나
            if visited[p] and not visited[c]:
                visited[c] = True # 방문하는 경우에 대해 끝까지 탐색 시작
                
                if info[c] == 0:
                    dfs(sheep+1, wolf)
                else:
                    dfs(sheep, wolf+1)
                
                visited[c] = False # 방문하지 않는 경우로 바꾸어 다음 경우의 수들을 탐색 시작
                
    dfs(1, 0)
    
    return max(ans)