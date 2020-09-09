# 알파코드

def dfs(l, word):
    global cnt

    if l == len(code):
        print(word)
        cnt += 1
    else:
        dfs(l+1, word + chr(int(code[l]) + 64))
        if l < len(code) - 1:
            if code[l] == '1':
                dfs(l+2, word + chr(int(code[l:l+2]) + 64))
            elif code[l] == '2' and int(code[l+1]) < 7:
                dfs(l+2, word + chr(int(code[l:l+2]) + 64))

if __name__ == '__main__':
    code = input()

    cnt = 0
    dfs(0, '')
    print(cnt)
