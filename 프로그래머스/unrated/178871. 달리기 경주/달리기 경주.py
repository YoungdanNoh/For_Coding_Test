def solution(players, callings):
    # players : 선수들의 이름이 1등부터 현재 등수 순서대로 담긴 문자열 배열
    # callings : 해설진이 부른 이름을 담은 문자열 배열
    # 배열 swap으로 풀려하면 시간 초과로 실패함
    # HashMap은 특정 데이터에 접근할 때 key로 접근하기 때문에 시간복잡도가 O(1)이다.
    # 배열에서 특정 데이터에 접근할 때의 시간 복잡도는 O(n)이다.
    # 따라서 배열 자료구조를 사용하면 callings의 길이가 길수록 실행 시간은 길어진다.
    
    dic = {string : i for i, string in enumerate(players)}
    
    for i in callings:
        # pre : 앞 선수 이름의 현재 인덱스
        # porst : 해설진이 부른 선수 이름의 현재 인덱스
        pre, post = dic[i] - 1, dic[i]
        dic[players[pre]] = post
        dic[players[post]] = pre
        # 두 선수의 인덱스 번호를 서로 바꿔준다.
        players[pre], players[post] = players[post], players[pre]
        
    return players
