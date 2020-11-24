# 토마토
from collections import deque

m, n = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

day = [[0] * m for _ in range(n)]

q = deque()
for i in range(n):
    for j in range(m):
        if board[i][j] == 1:
            q.append([i, j])
        if board[i][j] == -1:
            day[i][j] = -1

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

while q:
    y, x = q.popleft()

    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]

        if 0 <= ny < n and 0 <= nx < m:
            if board[ny][nx] == 0:
                board[ny][nx] = 1
                day[ny][nx] = day[y][x] + 1
                q.append([ny, nx])

max_day = 0
for i in range(n):
    for j in range(m):
        if board[i][j] == 0:
            print(-1)
            exti(0)
        
        max_day = max(max_day, day[i][j])

print(max_day)
        