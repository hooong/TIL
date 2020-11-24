# 창고 정리

l = int(input())
box = list(map(int, input().split()))
m = int(input())

for _ in range(m):
    box.sort()
    box[0] += 1
    box[-1] -= 1

print(max(box) - min(box))
