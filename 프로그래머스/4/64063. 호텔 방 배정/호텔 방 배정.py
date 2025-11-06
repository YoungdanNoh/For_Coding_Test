from collections import defaultdict
import sys

# 재귀 한도 늘리기
sys.setrecursionlimit(100000)

def solution(k, room_number):
    
    arr = defaultdict()
    ans = []
    
    def find(rn):
        # rn 다음 방 번호 찾기
        next_rn = arr[rn]
        
        if next_rn not in arr:
            # 다음 방 번호로 방 배정
            arr[next_rn] = next_rn+1
            return next_rn
        
        tmp = find(next_rn)
        arr[rn] = tmp+1
        return tmp
            
        
    for i in range(len(room_number)):
        rn = room_number[i]
        
        if rn not in arr:
            ans.append(rn)
            # 다음에 rn이라는 방 번호를 요청하는 사람이 있다면 rn+1 방을 배정해야 함
            arr[rn] = rn+1
        else:
            ans.append(find(rn))
        
    return ans