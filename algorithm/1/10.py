# 점수계산

n = int(input())
marks = list(map(int, input().split()))

total_score = 0
score = 0
is_continuous = False
for mark in marks:
    if mark == 0:
        score = 0
        is_continuous = False
    else:
        score += 1
        is_continuous = True

        total_score += score
print(total_score)
