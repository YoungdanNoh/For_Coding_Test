# 처음 작업은 0초에 시작하여 코어에 배정된다.
def solution(n, cores):
    
    # 코어갯수가 작업갯수보다 같거나 크다면 n번째 코어 return
    if len(cores) >= n:
        return n
    
    # 이분 탐색 구간: 0 ~ (가장 긴 작업 시간) * (남은 작업 수)
    lo, hi = 0, max(cores) * (n - len(cores))
    
    def job_count(time):
        # 각 코어마다 작업이 배정된 횟수
        return sum(time//c + 1 for c in cores)
    
    t = 0 # 마지막 작업이 배정되는 가장 빠른 시간
    while lo <= hi:
        mid = (lo+hi) // 2
        
        if job_count(mid) >= n:
            hi = mid - 1
            t = mid
        else:
            lo = mid + 1
            
    # 직전 시각까지 배정된 개수
    prev = sum((t - 1) // c + 1 for c in cores)
    remain = n - prev  # n번째 작업까지 남은 작업의 횟수
    '''
    t시점엔 무조건 n번째 작업이 배정되는건 확실하다(이분탐색의 이유)
    t-1번째 이후 t시점에 몇개의 작업이 배정될지는 모른다. 
    하지만 그 작업들 중에서 n-prev번째가 n번째 작업이 된다.
    '''
    
    # 시각 t에 작업이 막 끝난(=바로 새 작업 시작 가능한) 코어를 인덱스 오름차로 스캔
    for i, c in enumerate(cores):
        if t % c == 0:
            remain -= 1
            if remain == 0:
                return i + 1  # 1-based 코어 번호
    