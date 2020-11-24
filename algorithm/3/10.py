# 역수열

n = int(input())
arr = list(map(int, input().split()))

answer = [0 for _ in range(n)]
for i in range(1, n+1):
    cnt = arr[i-1]

    j = 0
    while cnt > 0:
        if answer[j] == 0:
            cnt -= 1
        j += 1
    
    while answer[j] != 0:
        j += 1
    
    answer[j] = i
print(*answer)
    