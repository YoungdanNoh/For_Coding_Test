import sys

a, b = map(int, sys.stdin.readline().split())

if(a < b):
    tmp = a
    a = b
    b = tmp

n1, n2 = a, b
while n2 > 0:
    n1, n2 = n2, (n1 % n2)

print(n1)
print(int((a*b)/n1))