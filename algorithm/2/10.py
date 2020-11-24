# 스도쿠 검사

def is_correct(nums):
    for num in range(1,10):
        if not num in nums:
            return False
    return True

sudoku = []
for _ in range(9):
    sudoku.append(list(map(int, input().split())))

# 각 행 검사
for r in range(9):
    row = sudoku[r]

    if not is_correct(row):
        print("NO")
        exit()

# 각 열 검사
for c in range(9):
    col = []
    for i in range(9):
        col.append(sudoku[c][i])

    if not is_correct(col):
        print("NO")
        exit()

# 각 사각형 검사
for r in range(3):
    for c in range(3):
        box = []
        for i in range(r*3, r*3+3):
            for j in range(c*3, c*3+3):
                box.append(sudoku[i][j])

        if not is_correct(box):
            print("NO")
            exit()

print("YES")