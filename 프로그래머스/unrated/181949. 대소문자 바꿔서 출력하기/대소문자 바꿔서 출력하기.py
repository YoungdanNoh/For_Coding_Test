str = input()
temp = list(str)

for i in range(len(str)):
    if str[i].islower():
        temp[i] = temp[i].upper()
    elif str[i].isupper():
        temp[i] = temp[i].lower()

print("".join(temp))