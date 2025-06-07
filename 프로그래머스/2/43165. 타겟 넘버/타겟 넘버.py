answer = 0

def solution(numbers, target):
    # + or -를 선택
    # 부분 집합들을 모두 구해서 구해진 애들을 - or + 하는 모든 경우의 수 구하기
    
    R = len(numbers)
    is_selected = [False] * R
    
    def subSet(cnt):
        global answer
        
        if(cnt == R):
            tmp = 0
            
            for i in range(R):
                if(is_selected[i]):
                    # 구해진 애들을 +
                    tmp += numbers[i]
                else:
                    tmp -= numbers[i]
                    
            if(tmp == target):
                answer += 1
                
            return
        
        is_selected[cnt] = True # 선택하거나
        subSet(cnt+1)
            
        is_selected[cnt] = False # 선택을 안 하거나
        subSet(cnt+1)
            
    subSet(0)
    
    return answer