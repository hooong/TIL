# 사다리 타기

dx = [-1, 1, 0]
dy = [0, 0, -1]

def dfs(y, x):
    if y == 0:
        print(x)

    is_turn = False
    for i in range(3):
        ny = y + dy[i]
        nx = x + dx[i]

        if 0 <= ny < 10 and 0 <= nx < 10 and not is_turn:
            if board[ny][nx] == 1:
                is_turn = True
                board[ny][nx] = -1
                dfs(ny, nx)
                board[ny][nx] = 1

if __name__ == '__main__':
    board = [list(map(int, input().split())) for _ in range(10)]

    target = board[9].index(2)
    dfs(9, target)
