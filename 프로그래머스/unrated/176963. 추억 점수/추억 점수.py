def solution(name, yearning, photo):
    # name : 그리워하는 사람의 이름을 담은 문자열 배열
    # yearning : 각 사람별 그리움 점수를 담은 정수 배열
    # photo : 각 사진에 찍힌 인물의 이름을 담은 이차원 문자열 배열
    
    answer = []
    for p in photo:
        score = 0
        for p_i in p: # 배열 안의 이름들을 하나씩 순회
            try:
                i = name.index(p_i) # 그리워하는 사람이라면
                score = score + yearning[i] # 점수를 더한다
            except:
                pass
        answer.append(score)
    
    return answer