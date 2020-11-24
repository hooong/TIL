# 재귀함수를 이용한 이진수 출력

def to_binary(num):
    if num == 0:
        return 0
    elif num == 1:
        return 1
    else:
        return str(to_binary(num // 2)) + str(num % 2)

n = int(input())

print(to_binary(n))
