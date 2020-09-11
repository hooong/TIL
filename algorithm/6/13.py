# 섬나라 아일랜드
from collections import deque

dx = [-1, 0, 1, 0, -1, -1, 1, 1]
dy = [0, 1, 0, -1, -1, 1, -1, 1]

def bfs(y, x):
    q = deque()
    q.append([y, x])

    while q:
        y, x = q.popleft()

        for i in range(8):
            ny = y + dy[i]
            nx = x + dx[i]

            if 0 <= ny < n and 0 <= nx < n:
                if board[ny][nx] == 1:
                    board[ny][nx] = 0
                    q.append([ny, nx])

if __name__ == '__main__':
    n = int(input())
    board = [list(map(int, input().split())) for _ in range(n)]

    cnt = 0
    for i in range(n):
        for j in range(n):
            if board[i][j] == 1:
                board[i][j] = 0
                cnt += 1
                bfs(i, j)

print(cnt)
