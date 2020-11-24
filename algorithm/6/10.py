# 미로탐색

def dfs(y, x):
    global cnt

    dx = [-1, 0, 1, 0]
    dy = [0, -1, 0, 1]
    if y == 6 and x == 6:
        cnt += 1
        return 
    else:
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]

            if 0 <= ny < 7 and 0 <= nx < 7:
                if board[ny][nx] == 0:
                    board[ny][nx] = -1
                    dfs(ny, nx)
                    board[ny][nx] = 0

if __name__ == '__main__':
    board = []
    for _ in range(7):
        board.append(list(map(int, input().split())))

    cnt = 0
    board[0][0] = -1
    dfs(0, 0)
    print(cnt)
