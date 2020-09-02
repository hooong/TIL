# 사과나무(다이아몬드)

n = int(input())

apples = []
for _ in range(n):
    apples.append(list(map(int, input().split())))

quantity = 0
# 윗 삼각형
j = n // 2
for i in range(n//2 + 1):
    start = j
    for _ in range((i * 2) + 1):
        quantity += apples[i][start]
        start += 1
    j -= 1

# 아래 삼각형
j = 1
for i in range(n-(n//2), n):
    start = j
    for _ in range((n-i)*2-1):
        quantity += apples[i][start]
        start += 1
    j += 1

print(quantity)