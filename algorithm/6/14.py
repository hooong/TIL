# 안전영역
import sys
from copy import deepcopy

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

def dfs(y, x):
    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]

        if 0 <= ny < n and 0 <= nx < n:
            if board[ny][nx] > h:
                tmp = board[ny][nx]
                board[ny][nx] = 0
                dfs(ny, nx)

if __name__ == '__main__':
    n = int(input())
    init_board = [list(map(int, input().split())) for _ in range(n)]

    min_h = sys.maxsize
    max_h = 0
    for i in range(n):
        for j in range(n):
            min_h = min(min_h, init_board[i][j])
            max_h = max(max_h, init_board[i][j]) 

    max_safe = 0
    board = []
    for h in range(min_h, max_h+1):
        board = deepcopy(init_board)
        cnt = 0
        for i in range(n):
            for j in range(n):
                if board[i][j] > h:
                    tmp = board[i][j]
                    board[i][j] = 0
                    cnt += 1
                    dfs(i, j)
        max_safe = max(max_safe, cnt)

    print(max_safe)
