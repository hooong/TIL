# 미로의 최단거리 통로
from collections import deque

board = []
for _ in range(7):
    board.append(list(map(int, input().split())))

q = deque()
q.append([0, 0, 0])
board[0][0] = -1

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
while q:
    cur_y, cur_x, cnt = q.popleft()

    for i in range(4):
        ny = cur_y + dy[i]
        nx = cur_x + dx[i]

        if ny == 6 and nx == 6:
            print(cnt+1)
            exit(0)

        if 0 <= ny < 7 and 0 <= nx < 7:
            if board[ny][nx] == 0:
                q.append([ny, nx, cnt+1])
                board[ny][nx] = -1

print(-1)
