n = list(str(input()))
tmp = int(len(n) / 2)

a = list(map(int, n[0: tmp]))
b = list(map(int, n[tmp:]))

if sum(a) == sum(b):
    print("LUCKY")
else:
    print("READY")