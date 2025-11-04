from collections import defaultdict

def solution(gems):
    typeG = set(gems)
    dic = {} # 각 보석의 인덱스 만들어주기
    cnt = 0
    for t in typeG:
        dic[t] = cnt
        cnt += 1
    
    # 투 포인터 방식
    totalType = len(typeG)
    check = defaultdict(int)
    have = 0
    left = 0
    minLen = 100001
    ans = [100001, 100001]

    for right in range(len(gems)):
        # 오른쪽 포인터 확장
        if check[gems[right]] == 0:
            have += 1
        check[gems[right]] += 1

        # 모든 종류가 포함된 경우, 왼쪽을 줄이며 최소 구간 찾기
        while have == totalType:
            if (right - left + 1) < minLen:
                minLen = right - left + 1
                ans = [left + 1, right + 1]  # 1-based index

            check[gems[left]] -= 1
            if check[gems[left]] == 0:
                have -= 1
            left += 1

    return ans