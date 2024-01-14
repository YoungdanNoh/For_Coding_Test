arr = list(map(int, input().split()))
dna = input()
acgt = list(map(int, input().split()))

S = arr[0]
P = arr[1]

pw_cnt = 0
a,c,g,t = 0, 0, 0, 0

temp = dna[0:P] # 0,1
temp_l = list(temp)

A = temp_l.count('A')
C = temp_l.count('C')
G = temp_l.count('G')
T = temp_l.count('T')

if acgt[0] <= A and acgt[1] <= C and acgt[2] <= G and acgt[3] <= T:
    pw_cnt += 1

for i in range(1, S-P+1):
    #S-P+1  3 => 1,2
    if dna[P+i-1] == 'A':
        A += 1
    elif dna[P+i-1] == 'C':
        C += 1
    elif dna[P+i-1] == 'G':
        G += 1
    elif dna[P+i-1] == 'T':
        T += 1
        
    if dna[i-1] == 'A':
        A -= 1
    elif dna[i-1] == 'C':
        C -= 1
    elif dna[i-1] == 'G':
        G -= 1
    elif dna[i-1] == 'T':
        T -= 1
        
    if acgt[0] <= A and acgt[1] <= C and acgt[2] <= G and acgt[3] <= T:
        pw_cnt += 1

print(pw_cnt)