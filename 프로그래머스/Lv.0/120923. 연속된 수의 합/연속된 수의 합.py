def solution(num, total):
    answer = []
    if total%num == 0:
        mid = total/num
        for i in range(int((num-1)/2)): #0~1 == 0, 0~2 == 0,1
            answer.append(mid-(i+1))
            answer.append(mid+(i+1))
        answer.append(mid)
    else:
        mid1 = total//num
        mid2 = mid1+1
        answer.append(mid1)
        answer.append(mid2)
        
        for i in range(int((num-2)/2)): #0~1 == 0, 0~2 == 0,1
            answer.append(mid1-(i+1))
            answer.append(mid2+(i+1))
        
    answer.sort()
    return answer