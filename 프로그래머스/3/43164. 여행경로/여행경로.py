def solution(tickets):
    graph = dict()

    for (start, end) in tickets:
        if start in graph:
            graph[start].append(end)
        else:
            graph[start] = [end]
    
    for g in graph.keys():
        graph[g].sort()
    
    route = []
    
    def dfs(start):
        if start in graph:
            while graph[start]:
                dfs(graph[start].pop(0))
        route.append(start)

    dfs("ICN")
    
    return route[::-1]