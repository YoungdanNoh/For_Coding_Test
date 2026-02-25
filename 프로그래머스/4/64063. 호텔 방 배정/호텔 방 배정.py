import sys

# 재귀 한도 늘리기
sys.setrecursionlimit(100000)

next = {}
def find(require):
    if require not in next:
        # 해당 방 번호에 배정된 사항 없으므로 해당 방을 배정
        next[require] = require + 1 # 해당 방 번호를 또 요청한다면 이후로는 require+1번 방부터 배정 가능
        return require
    
    empty = find(next[require]) # 재귀를 돌면서 빈 방 찾기
    next[require] = empty + 1
    return empty
    

def solution(k, room_number):
    ans = [0 for _ in range(len(room_number))]
    for i in range(len(room_number)):
        ans[i] = find(room_number[i])
    
    return ans