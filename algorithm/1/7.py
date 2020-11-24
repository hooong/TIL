# 소수 (에라토스테네스 체)

n = int(input())

eratos = [False for _ in range(n+1)]
eratos[0] = True
eratos[1] = True

for i in range(2,n):
    x = i * 2
    while x <= n:
        eratos[x] = True
        x += i

print(eratos.count(False))
