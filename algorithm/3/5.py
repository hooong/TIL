# 회의실 배정

n = int(input())

meetings = []
for _ in range(n):
    meetings.append(list(map(int, input().split())))

meetings.sort(key=lambda meeting: meeting[1])

cur = 1
cnt = 0
for meeting in meetings:
    if meeting[0] >= cur:
        cur = meeting[1]
        cnt += 1

print(cnt)