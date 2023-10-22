def solution(n):
    answer = 0
    
    if n%2 == 1:
        answer = [answer + i for i in range(1, n+1, 2)]
    else:
        answer = [answer + i**2 for i in range(0, n+1, 2)]
        
    return sum(answer)