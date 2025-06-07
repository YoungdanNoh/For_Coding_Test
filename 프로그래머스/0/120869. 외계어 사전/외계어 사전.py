def solution(spell, dic):
    
    R = len(spell)
    result = ["" for _ in range(R)]
    is_selected = [False] * R
    all_result = []
    
    def perm(cnt):
        if(cnt == R):
            tmp = "".join(result)
            all_result.append(tmp)
            return
        
        for i in range(R):
            if(is_selected[i]):
                continue
            
            is_selected[i] = True
            result[cnt] = spell[i]
            perm(cnt+1)
            
            is_selected[i] = False
    
    perm(0)
    
    for i in range(len(all_result)):
        if(all_result[i] in dic):
            return 1
        
    return 2