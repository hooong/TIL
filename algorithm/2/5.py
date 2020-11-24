# 수들의 합

n, m = map(int, input().split())
seq = list(map(int, input().split()))

count = 0
for i in range(n):
    for j in range(i,n+1):
        if m == sum(seq[i:j]):
            count += 1

print(count)
