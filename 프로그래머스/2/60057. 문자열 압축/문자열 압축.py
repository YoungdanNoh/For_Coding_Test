def solution(s):
    result = 2001
    
    for i in range(1, len(s)+1):
        re_s = ""
        tmp = s[0:i]
        start = 0
        end = i
        cnt = 1
        
        for j in range((len(s)//i)-1):
            com = s[start+i : end+i]
            start += i
            end += i
            if tmp == com:
                cnt += 1
                flag = True
            else:
                if cnt == 1:
                    re_s += tmp
                else:
                    re_s += tmp + str(cnt)
                tmp = com
                cnt = 1
                flag = False
        
        if cnt == 1:
            re_s += tmp
        else:
            re_s += tmp + str(cnt)
        if (len(s)%i) != 0:
            idx = int(len(s)%i)
            re_s += s[-idx:]
            
        result = min(result, len(re_s))
    
    return result