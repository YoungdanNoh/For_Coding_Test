def solution(m, n, puddles):
    
    dp = [[0]*m for _ in range(n)]
    
    for px, py in puddles:
        dp[py-1][px -1] = -1 # 물
        
    if m >= 2 and dp[0][1] != -1:
        dp[0][1] = 1
    if n >= 2 and dp[1][0] != -1:
        dp[1][0] = 1
        
    # 왼, 위
    dx = [0, -1]
    dy = [-1, 0]
        
    for i in range(n):
        for j in range(m):
            if i == 0 and j == 1:
                continue
            if i == 1 and j == 0:
                continue
                
            if dp[i][j] == -1: # 물길
                continue
            
            for k in range(2):
                tx = i + dx[k]
                ty = j + dy[k]
                
                if tx<0 or tx>=n or ty<0 or ty>=m:
                    continue
                
                if dp[tx][ty] == -1: # 물길
                    continue
                
                dp[i][j] += dp[tx][ty]
    
    return dp[n-1][m-1] % 1000000007