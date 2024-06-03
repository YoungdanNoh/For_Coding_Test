def solution(N, stages):
    fail = {}
    
    for i in range(1, N+1):
        arrive = 0
        no = 0
        for j in stages:
            if j >= i:
                # 스테이지에 도달한 사람
                arrive += 1
            if i == j:
                # 클리어하지 못한 사람
                no += 1
        if no > 0 and arrive > 0:
            fail[i] = no/arrive
        else:
            fail[i] = 0
        
    fail = sorted(fail.items(), key = lambda x: -x[1])
    # 내림차순 정렬
    
    result = []
    for f in fail:
        result.append(f[0])
    
    return result