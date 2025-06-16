def solution(phone_book):
    answer = True
    
    dic = {}
    lst = [] # 전화번호 길이 리스트
    for i in range(len(phone_book)):
        lst.append(len(phone_book[i]))
        
    lst = set(lst)
    
    for i in range(len(phone_book)):   
        # lst 길이만큼 잘라서 저장
        for j in lst:
            if(len(phone_book[i]) < j):
                continue
                
            if(phone_book[i][:j] in dic):
                dic[phone_book[i][:j]] += 1
                
            else:
                dic[phone_book[i][:j]] = 1
                
    for i in range(len(phone_book)):
        if phone_book[i] in dic:
            if(dic[phone_book[i]] >= 2):
            #print(dic[])
                answer = False
            
    
#     for i in range(len(phone_book)):
#         # 한 요소씩 선택
#         length = len(phone_book[i])
        
#         for j in range(len(phone_book)):
#             if(i == j):
#                 continue
                
#             if(len(phone_book[j]) < length):
#                 continue
            
#             tmp = phone_book[j][:length]
            
#             if(phone_book[i] == tmp):
#                 answer = False
#                 flag = True
#                 break
        
#         if flag:
#             break
    
    return answer