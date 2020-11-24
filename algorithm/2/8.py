# 곶감(모래시계)

n = int(input())

board = []
for _ in range(n):
    board.append(list(map(int, input().split())))

m = int(input())
for _ in range(m):
    row, direction, repeat = map(int, input().split())
    row -= 1

    # 왼쪽
    if direction == 0:
        for _ in range(repeat):
            board[row].append(board[row].pop(0))
    # 오른쪽
    else:
        for _ in range(repeat):
            board[row].insert(0,board[row].pop())

j = 0
result = 0
for i in range(n // 2 + 1):
    result += sum(board[i][j:n-j])
    j += 1

j = n // 2 - 1
for i in range(n // 2 + 1, n):
    result += sum(board[i][j:n-j])
    j -= 1

print(result)