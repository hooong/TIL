# 증가수열 만들기

n = int(input())
arr = list(map(int, input().split()))

answer = ''
last_num = 0
while arr:
    if last_num > arr[0] and last_num > arr[-1]:
        break
    elif last_num > arr[0] and last_num <= arr[-1]:
        answer += 'R'
        last_num = arr.pop()
    elif last_num <= arr[0] and last_num > arr[-1]:
        answer += 'L'
        last_num = arr.pop(0)
    else:
        if arr[0] > arr[-1]:
            answer += 'R'
            last_num = arr.pop()
        else:
            answer += 'L'
            last_num = arr.pop(0)

print(answer)
