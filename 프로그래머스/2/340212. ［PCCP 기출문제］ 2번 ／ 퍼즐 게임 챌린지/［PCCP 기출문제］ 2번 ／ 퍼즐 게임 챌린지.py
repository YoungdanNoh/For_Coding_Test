def solution(diffs, times, limit):
    ans = 0
    
    start = 1 # level의 최소값
    end = max(diffs) # level의 최대값
    
    while start <= end:
        mid = (start + end) // 2 # level
        
        t = 0
        for i in range(len(diffs)):
            if diffs[i] <= mid:
                t += times[i]
            else:
                incorrect = diffs[i] - mid
                t += (times[i] + times[i-1])*incorrect + times[i]
                
        if t <= limit:
            ans = mid
            end = mid - 1
        else:
            start = mid + 1
                
    return ans