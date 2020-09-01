# k번째 수

t = int(input())

for _ in range(t):
    n, s, e, k = map(int, input().split())
    arr = list(map(int, input().split()))

    arr = arr[s-1:e]
    arr.sort()

    print(arr[k-1])
