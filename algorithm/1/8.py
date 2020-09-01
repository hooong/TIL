# 뒤집은 소수

def reverse(x):
    x = str(x)
    return int(x[::-1])

def isPrime(x):
    for i in range(2,x):
        if x % i == 0:
            return False
    return True

n = int(input())
arr = list(map(int, input().split()))

for num in arr:
    reversed_num = reverse(num)

    if isPrime(reversed_num):
        print(reversed_num, end = ' ')
print()