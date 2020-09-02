# 숫자만 추출

digit = ['0','1','2','3','4','5','6','7','8','9']
s = input()

num = ''
for i in s:
    if i in digit:
        num += i

num = int(num)
count = 0
for i in range(1,num+1):
    if num % i == 0:
        count += 1

print(num)
print(count)
