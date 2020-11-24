# 사과나무
from collections import deque

n = int(input())

board = []
for _ in range(n):
    board.append(list(map(int, input().split())))

cnt = 0
q = deque()
q.append([n//2, n//2])
cnt += board[n//2][n//2]
board[n//2][n//2] = -1

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
while q:
    cur_x, cur_y = q.popleft()

    for i in range(4):
        nx = cur_x + dx[i]
        ny = cur_y + dy[i]
    
        if not board[ny][nx] == -1:
            cnt += board[ny][nx]
            board[ny][nx] = -1
            if 1 < cur_x < n-2 and 1 < cur_y < n-2:
                q.append([nx, ny])

print(cnt)
