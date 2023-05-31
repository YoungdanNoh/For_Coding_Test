def solution(k, score):
    # k: 명예의 전당 목록의 점수의 개수
    # score: 1일부터 마지막 날까지 출연한 가수들의 점수
    win = []
    answer = []
    for s in score:
        if len(win) < k:
            win.append(s)
        elif (len(win) % k == 0) and (s > min(win)):
            win.pop(win.index(min(win)))
            win.append(s)
        answer.append(min(win))
    
    return answer