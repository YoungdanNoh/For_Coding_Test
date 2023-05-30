def solution(t, p):
    num = []
    cnt = 0
    
    for i in range(len(t)-len(p)+1):
        num.append(t[i:i+len(p)])
    for n in num:
        if int(n) <= int(p):
            cnt += 1
        
    return cnt