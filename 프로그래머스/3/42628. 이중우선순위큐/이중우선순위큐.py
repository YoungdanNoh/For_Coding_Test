def solution(operations):
    
    # tmp = [1, 1, 3]
    # print(tmp.index(min(tmp)))
    
    q = []
    
    for i in operations:
        
        if i[0] == "I":
            q.append(int(i[2:]))
        
        elif i == "D 1" and len(q) > 0:
            q.pop(q.index(max(q)))
        
        elif i == "D -1" and len(q) > 0:
            q.pop(q.index(min(q)))
            
    answer = []
    
    if(len(q) == 0):
        answer = [0, 0]
    else:
        answer = [max(q), min(q)]
    
    return answer