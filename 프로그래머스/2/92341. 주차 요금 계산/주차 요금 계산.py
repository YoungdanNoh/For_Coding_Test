import math

def solution(fees, records):
    # fees: 기본시간, 기본요금, 단위 시간, 단위 요금
    # 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면 '올림'
    
    dic = {}
    timeDic = {}
    result = []
    # "IN"일 때 dic에 추가, "OUT"일 때 빼서 시간 계산
    for r in records:
        time = int(r[:2])*60 + int(r[3:5])
        num = r[6:11]
        
        if r[11:] == "IN":
            if num in dic:
                dic[num].append(time)
            else:
                dic[num] = []
                dic[num].append(time)
        
        elif r[11:] == "OUT":
            inTime = dic[num].pop()
            pTime = time - inTime # 주차한 시간
            
            if num in timeDic:
                timeDic[num] += pTime
            else:
                timeDic[num] = pTime
                
    for k, v in dic.items():
        
        if(len(v) > 0):
            pTime = (60*23 + 59) - v[0] # 주차한 시간

            if k in timeDic:
                timeDic[k] += pTime
            else:
                timeDic[k] = pTime
    
    timeDic = sorted(timeDic.items(), key = lambda x: x[0]) # 정렬
    
    for n, t in timeDic:
        fee = 0
        
        if t <= fees[0]:
            # 기본 요금
            fee = fees[1]
        else:
            # 추가 요금 발생
            fee = math.ceil((t-fees[0]) / fees[2]) * fees[3] + fees[1]

        result.append(fee)

    return result