def solution(n, times):
    
    start = 0 # 최소 시간
    end = 1000000000*1000000000 # 최대 시간
    mid = 0
    
    answer = 0
    
    while start<=end:
        mid = int((start + end)/2) # 심사 시간
        cnt = 0 # 시간 내에 심사 가능한 사람의 수
        
        for i in times:
            cnt += int(mid/i)
            
        if(cnt >= n):
            # 인원 수보다 많은 사람들 체크 가능
            answer = mid
            end = mid-1
        else:
            start = mid+1
    
    return answer