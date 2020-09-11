# 단지 번호 붙이기

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

def dfs(y, x):
    global cnt

    cnt += 1
    danji[y][x] = 0

    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]

        if 0 <= ny < n and 0 <= nx < n:
            if danji[ny][nx] == 1:
                dfs(ny, nx)

if __name__ == '__main__':
    n = int(input())

    danji = [list(map(int, input())) for _ in range(n)]

    house = []
    for i in range(n):
        for j in range(n):
            if danji[i][j] == 1:
                cnt = 0
                dfs(i, j)
                house.append(cnt)

    house.sort()
    print(len(house))
    print(*house, sep='\n')
