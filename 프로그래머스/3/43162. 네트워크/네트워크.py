def solution(n, computers):
    
    parent = [0]*n
    for i in range(n):
        parent[i] = i
        
    def find(x):
        if parent[x] == x:
            return x
        
        parent[x] = find(parent[x])
        return parent[x]
    
    def union(x, y):
        a = find(x)
        b = find(y)
        
        if a == b:
            return False
        
        if a < b:
            parent[b] = a
        else:
            parent[a] = b
        return True
    
    for i in range(n):
        for j in range(n):
            if i == j:
                continue
                
            if computers[i][j] == 1:
                # 두 컴퓨터를 잇는다
                union(i, j)
    
    # 모든 컴퓨터에 대해 find를 수행하여 최상위 루트를 가르키도록 한다.
    for i in range(n):
        find(i)
                
    return len(set(parent))