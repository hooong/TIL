# 가장 큰 수

num, m = map(int, input().split())
num = str(num)

stack = [num[0]]
for i in range(1, len(num)):
    while m > 0 and stack:
        if stack[-1] < num[i]:
            stack.pop()
            m -= 1
    stack += num[i]ß

while m > 0:
    stack.pop()
    m -= 1

print(*stack, sep='') 
