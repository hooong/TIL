# 회문 문자열 검사

n = int(input())

for _ in range(n):
    s = input()
    s = s.upper()
    reversed_s = s[::-1].upper()

    if s == reversed_s:
        print("YES")
    else:
        print("NO")
