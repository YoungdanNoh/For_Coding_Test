from collections import defaultdict

def solution(gems):
    typeG = set(gems)
    dic = {} # 각 보석의 인덱스 만들어주기
    cnt = 0
    for t in typeG:
        dic[t] = cnt
        cnt += 1
    
    total = len(typeG)
    minLen = 100001
    ans = [100001, 100001]
    
    start = 0 # 시작 인덱스
    gcnt = 0 # 현재 가지고 있는 보석 종류의 수
    visited = defaultdict(int) # 포함한 보석 수 세기
    
    for end in range(len(gems)):
        if visited[gems[end]] == 0:
            # 현재 가지고 있지 않은 보석이라면 보석 종류 수 +1
            gcnt += 1
        visited[gems[end]] += 1
        
        while gcnt == total:
            # 보석 종류를 모두 가진 상태라면 왼쪽을 줄여보기
            if (end-start+1) < minLen:
                minLen = end-start+1
                ans = [start+1, end+1]
                
            visited[gems[start]] -= 1
            if visited[gems[start]] == 0:
                gcnt -= 1 # 가지고 있는 보석 종류 하나 감소
            start += 1
            
        
    return ans