# 씨름 선수

n = int(input())

players = []
for _ in range(n):
    players.append(list(map(int, input().split())))

players.sort(reverse=True)

cnt = 0
largest = 0
for h, w in players:
    if w > largest:
        largest = w
        cnt += 1
        
print(cnt) 
