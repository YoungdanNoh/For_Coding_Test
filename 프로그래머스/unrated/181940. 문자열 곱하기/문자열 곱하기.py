def solution(my_string, k):
    answer = ''
    
    answer = ''.join([answer + my_string for i in range(k)])
    
    return answer