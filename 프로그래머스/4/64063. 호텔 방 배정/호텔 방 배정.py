import sys

# 재귀 한도 늘리기
sys.setrecursionlimit(100000)

next = {}
def find(require):
    if require not in next:
        # 고객이 요청하는 방이 비어있음
        next[require] = require+1
        return require
    
    # 고객이 요청하는 방을 줄 수 없음
    possible = find(next[require]) # 다음 빈 방 찾기
    next[require] = possible+1
    return possible
    
    
def solution(k, room_number):
    ans = [0 for _ in range(len(room_number))]
    for i in range(len(room_number)):
        ans[i] = find(room_number[i]) # 각 요청 별 방 번호 구하기
    
    return ans