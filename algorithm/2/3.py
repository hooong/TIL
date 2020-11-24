# 카드 역배치

cards = [i for i in range(21)]

for _ in range(10):
    ai, bi = map(int, input().split())
    cards = cards[0:ai] + cards[bi:ai-1:-1] + cards[bi+1:]

cards = cards[1:]
print(*cards)