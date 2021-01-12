# 몬티홀의 역설 실험 코드
import random


REPEAT = 1000000
HALL = ['SPORTS_CAR', 'GOAT', 'GOAT']

count = 0
for i in range(REPEAT):
    nums = [False] * 3
    random.shuffle(HALL)

    pick = random.randrange(0, 3)
    nums[pick] = True

    for j in range(0, 3):
        if j == pick:
            continue

        if HALL[j] == 'GOAT':
            nums[j] = True
            break

    for j in range(0,3):
        if nums[j]:
            continue
        re_pick = j

    if HALL[re_pick] == 'SPORTS_CAR':
        count += 1

print((count / REPEAT) * 100)
