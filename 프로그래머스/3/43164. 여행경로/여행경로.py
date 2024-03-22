def solution(tickets):
    answer = []
    
    graph = dict()
    
    for start, end in tickets:
        if start in graph:
            graph[start].append(end)
        else:
            graph[start] = [end]
    
    for g in graph:
        graph[g].sort()
    
    def dfs(graph, start):
        if start in graph:
            while graph[start]:
                dfs(graph, graph[start].pop(0))
        answer.append(start)
            
    dfs(graph, "ICN")
    
    return answer[::-1]