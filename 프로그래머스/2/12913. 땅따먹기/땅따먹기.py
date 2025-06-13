def solution(land):
    
    dp = [land[0]] + [[0]*4 for i in range(len(land)-1)]
    
    for i in range(1, len(land)):
        for j in range(4):
            tmp = 0
            for k in range(4):
                if(k != j):
                    tmp = max(tmp, dp[i-1][k])
            
            dp[i][j] = land[i][j] + tmp
    
    return max(dp[len(land)-1])