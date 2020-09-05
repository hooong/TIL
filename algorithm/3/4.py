# 마구간 정하기

def is_possible(distance):
    count = c
    before = 0

    for i in range(len(stables)):
        if count == 0:
            return True

        if i == 0:
            before = stables[i]
            count -= 1
            continue
            
        if stables[i] - before >= distance:
            count -= 1
            before = i
    return False

n, c = map(int, input().split())

stables = []
for _ in range(n):
    stables.append(int(input()))
stables.sort()

start = 1
end = stables[-1] - stables[0]
while start <= end:
    mid = (start + end) // 2

    if is_possible(mid):
        answer = mid
        start = mid + 1
    else:
        end = mid - 1

print(answer)