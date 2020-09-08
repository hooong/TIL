# 후위식 연산

exp = input()
num = ['1', '2', '3', '4', '5', '6', '7', '8', '9']

stack = []
for e in exp:
    if e in num:
        stack.append(int(e))
    else:
        right = stack.pop()
        left = stack.pop()

        if e == '+':
            stack.append(left + right)
        elif e == '-':
            stack.append(left - right)
        elif e == '*':
            stack.append(left * right)
        elif e == '/':
            stack.append(left // right)
            
print(stack[0])