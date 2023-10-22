def solution(my_string, overwrite_string, s):
    last = s + len(overwrite_string)
    my_string = list(my_string)
    
    for i in range(s, last):
        my_string[i] = overwrite_string[i-s]
    
    answer = ''.join(my_string)
    return answer