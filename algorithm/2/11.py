# 격자판 회문수

def is_palindrome(sub):
    tmp = sub[::-1]
    if sub == tmp:
        return True
    return False

board = []
for _ in range(7):
    board.append(list(map(int, input().split())))

count_of_palindrome = 0
# 가로 확인
for i in range(7):
    for j in range(3):
        sub = board[i][j:j+5]
        if is_palindrome(sub):
            count_of_palindrome += 1

# 세로 확인
for i in range(7):
    for j in range(3):
        sub = []
        for k in range(j, j+5):
            sub.append(board[k][i])
        if is_palindrome(sub):
            count_of_palindrome += 1

print(count_of_palindrome)