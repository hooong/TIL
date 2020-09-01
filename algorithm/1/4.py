# 대표값
import sys

n = int(input())
scores = list(map(int, input().split()))

avg = round(sum(scores) / n)

diff = sys.maxsize
min_diff_index = 0
for i in range(n):
    cur_diff = abs(avg - scores[i])

    if cur_diff < diff:
        min_diff_index = i
        diff = cur_diff
    elif cur_diff == diff:
        if scores[i] > scores[min_diff_index]:
            min_diff_index = i
        else:
            continue

print(avg, min_diff_index+1)
