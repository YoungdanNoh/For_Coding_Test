def solution(cards1, cards2, goal):
    # cards1, cards2 : 문자열로 이루어진 배열
    # goal : 원하는 단어 배열
    
    cnt = 0
    for g in goal:
        if len(cards1) > 0:
            if g == cards1[0]:
                cards1.pop(0)
                cnt += 1
        if len(cards2) > 0:
            if g == cards2[0]:
                cards2.pop(0)
                cnt += 1
            
    if len(goal) == cnt:
        answer = 'Yes'
    else:
        answer = 'No'
    
    return answer