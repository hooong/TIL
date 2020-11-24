# 등산경로

def dfs(y, x):
    global cnt 

    if y == n-1 and x == n-1:
        cnt += 1
        return 

    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]

        if 0 <= ny < n and 0 <= nx < n:
            if mountain[ny][nx] > mountain[y][x]:
                dfs(ny, nx)    

if __name__ == '__main__':
    n = int(input())

    mountain = []
    for _ in range(n):
        mountain.append(list(map(int, input().split())))

    cnt = 0
    dx = [-1, 0, 1, 0]
    dy = [0, -1, 0, 1]
    dfs(0, 0)

    print(cnt)