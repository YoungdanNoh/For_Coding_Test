def solution(n, computers):
    visited = [False] * n
                
    result = 0
    for i in range(n):
        if visited[i] == False:
            dfs(computers, i, visited, n)
            result += 1
        
    return result

def dfs(computers, i, visited, n):
    visited[i] = True
    
    for j in range(n):
        if visited[j] == False and j != i and computers[j][i] == 1:
            dfs(computers, j, visited, n)