def solution(routes):
    
    routes.sort(key=lambda x:x[1])
    
    cnt = 1
    camera = routes[0][1] # 진출 지점에 카메라 설치
    
    for i in range(1, len(routes)):
        if routes[i][0] <= camera <= routes[i][1]:
            continue
        else:
            cnt += 1
            camera = routes[i][1]
    
    return cnt