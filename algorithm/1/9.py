# 주사위 게임

n = int(input())

all_reward = []
for _ in range(n):
    dies = list(map(int, input().split()))
    dies_eyes_count = len(set(dies))

    if dies_eyes_count == 1:
        reward = 10000 + (dies[0] * 1000)
    elif dies_eyes_count == 2:
        for i in dies:
            if dies.count(i) == 2:
                same_eye = i
                break
        reward = 1000 + (same_eye * 100)
    else:
        reward = max(dies) * 100
    all_reward.append(reward)
print(max(all_reward))
