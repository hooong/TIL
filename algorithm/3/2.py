# 랜선자르기 (결정알고리즘)

def count(length):
    count = 0
    for line in lines:
        count += (line // length)

    return count

k, n = map(int, input().split())

lines = []
for _ in range(k):
    lines.append(int(input()))

answer = 0
start = 1
end = max(lines)
while start <= end:
    mid = (start + end) // 2

    if count(mid) >= n:
        answer = mid
        start = mid + 1
    else:
        end = mid - 1

print(answer)
