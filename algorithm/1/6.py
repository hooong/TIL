# 자릿수의 합

def digit_sum(x):
    result = 0

    for i in str(x):
        result += int(i)

    return result

n = int(input())
nums = list(map(int, input().split()))

max_sum = [-1, -1]
for num in nums:
    digit = digit_sum(num)
    if digit > max_sum[1]:
        max_sum = [num, digit]

print(max_sum[0])
