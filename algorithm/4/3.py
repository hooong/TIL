# 후위표기식 만들기

exp = input()
num = ['1', '2', '3', '4', '5', '6', '7', '8', '9']

stack = []
result = ''
for e in exp:
    if e in num:
        result += e
    elif e in ['+', '-', '*', '/']:
        while stack and stack[-1] in ['*', '/']:
            result += stack.pop()
        stack.append(e)
    elif e == '(':
        stack.append(e)
    elif e == ')':
        while not stack[-1] == '(':
            result += stack.pop()
        stack.pop()

while stack:
    result += stack.pop()

print(result)
