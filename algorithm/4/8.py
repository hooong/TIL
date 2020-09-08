# 단어 찾기

n = int(input())

words = []
for _ in range(n):
    words.append(input())

for _ in range(n-1):
    words.remove(input())

print(words[0])