# 쇠막대기

lazer = input()

stack = []
before_open = True
cnt = 0
stick = 0
for i in lazer:
    if i == '(':
        before_open = True
        stack.append(i)
        cnt += 1
        stick += 1
    elif i == ')':
        if before_open:
            stick += (cnt - 2) 
        stack.pop()
        before_open = False
        cnt -= 1
print(stick)
