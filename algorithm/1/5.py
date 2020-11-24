# 정다면체

n, m = map(int, input().split())

probs = dict()
for i in range(1, n+1):
    for j in range(1, m+1):
        sum_dies = i + j

        if sum_dies in probs.keys():
            probs[sum_dies] += 1
        else:
            probs[sum_dies] = 1

max_prob = max(probs.values())

answers = []
for p in probs.items():
    if p[1] == max_prob:
        answers.append(p[0])

print(*answers)
