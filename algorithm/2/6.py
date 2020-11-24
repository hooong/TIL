# 격자판 최대합

n = int(input())
board = []

for _ in range(n):
    board.append(list(map(int, input().split())))

# 행의 합
sum_of_rows = []
for i in range(n):
    sum_of_rows.append(sum(board[i]))
max_row = max(sum_of_rows)

# 열의 합
sum_of_cols = []
for i in range(n):
    tmp = 0
    for j in range(n):
        tmp += board[i][j]
    sum_of_cols.append(tmp)
max_col = max(sum_of_cols)

# 두 대각선의 합
lcross_of_sum = 0
rcross_of_sum = 0
for i in range(n):
    lcross_of_sum += board[i][i]
    rcross_of_sum += board[i][(n-1)-i]
max_cross = max(lcross_of_sum, rcross_of_sum)

print(max(max_cross, max(max_col, max_row)))
