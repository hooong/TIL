# 응급실

n, m = map(int, input().split())
patients = list(map(int, input().split()))

for i in range(len(patients)):
    patients[i] = [patients[i], i]

cnt = 0
while patients:
    danger, order = patients.pop(0)
    is_danger = True

    for d, o in patients:
        if d > danger:
            is_danger = False
            break
    
    if is_danger:
        cnt += 1
        if order == m:
            break
    else:
        patients.append([danger, order])

print(cnt)
