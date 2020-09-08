# 공주 구하기

n, k = map(int, input().split())

queue = []
for i in range(n):
    queue.append(i+1)

while len(queue) != 1:
    for i in range(k):
        queue.append(queue.pop(0))
    queue.pop()

print(queue[0])