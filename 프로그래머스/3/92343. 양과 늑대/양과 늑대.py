def solution(info, edges):
    
    visited = [False]*len(info)
    result = []
    
    def dfs(sheep, wolf):
        if(sheep <= wolf):
            return
        else:
            result.append(sheep)
            
        for parent, child in edges:
            if(visited[parent] and not visited[child]):
                # 부모는 방문했지만, 자식을 방문하지 않은 경우 해당 자식을 방문 or 안 함
                visited[child] = True
                
                if(info[child] == 0):
                    dfs(sheep+1, wolf)
                else:
                    dfs(sheep, wolf+1)
                
                visited[child] = False
    
    visited[0] = True
    dfs(1, 0)
    
    return max(result)