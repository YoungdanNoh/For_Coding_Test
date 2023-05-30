def solution(s):
    answer = []
    alpha = []
    
    for i in range(len(s)):
        temp = s[0:i]
        
        if s[i] in alpha:
            answer.append(i - temp.rindex(s[i]))
            alpha.append(s[i])
        else:
            alpha.append(s[i])
            answer.append(-1)
    
    return answer