import heapq

def solution(jobs):
    
    start = -1 # 마지막으로 작업이 대기 큐에 들어온 시간
    now = 0 # 현재 시간
    cnt = 0 # 작업 완료 갯수
    hq = []
    tt = 0
    
    while cnt < len(jobs):
        for i in range(len(jobs)):
            if(start < jobs[i][0] <= now):
                heapq.heappush(hq, (jobs[i][1], jobs[i][0]))
                
        if hq:
            c = heapq.heappop(hq)
            start = now
            now += c[0]
            tt += now - c[1]
            cnt += 1
        else:
            now += 1
            
    return tt // len(jobs)