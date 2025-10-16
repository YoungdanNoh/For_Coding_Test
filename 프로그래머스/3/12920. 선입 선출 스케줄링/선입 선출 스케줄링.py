import heapq

def solution(n, cores):
    
    # 코어갯수가 작업갯수보다 같거나 크다면 n-1번째 코어 return
    if len(cores) >= n:
        return n
    
    # 이분 탐색 구간: 0 ~ (가장 느린 코어 주기) * (남은 작업 수)
    lo, hi = 0, max(cores) * (n - len(cores))

    # done(t): t초까지 배정된(시작된) 작업 수
    def done(t):
        return sum(t // c + 1 for c in cores)

    # 가장 작은 t 찾기: done(t) >= n
    t = 0
    while lo <= hi:
        mid = (lo + hi) // 2
        if done(mid) >= n:
            hi = mid - 1
            t = mid
        else:
            lo = mid + 1

    # 직전 시각까지 배정된 개수
    prev = sum((t - 1) // c + 1 for c in cores)
    need = n - prev  # 시각 t에 막 끝난 코어들 중 need번째
    
    # 시각 t에 막 끝난(=바로 새 작업 시작 가능한) 코어를 인덱스 오름차로 스캔
    for i, c in enumerate(cores):
        if t % c == 0:
            need -= 1
            if need == 0:
                return i + 1  # 1-based 코어 번호
    