def solution(genres, plays):
    
    genr = { g : 0 for g in set(genres) }
    
    for i in range(len(genres)):
        genr[genres[i]] += plays[i]

    genr = sorted(genr.items(), key = lambda x: -x[1])
    
    answer = []
    
    for g, p in genr:
        tmp = []
        
        for i in range(len(genres)):
            if g == genres[i]:
                tmp.append((i, plays[i]))
                
        tmp.sort(key = lambda x: (-x[1], x[0]))
        
        if len(tmp) != 1:
            tmp = tmp[:2]
        
        for idx, play in tmp:
            answer.append(idx)
                
    return answer