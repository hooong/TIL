# [Linux] Shell과 기본 명령어

- #### Shell(쉘) 이란?

  > 사용자 명령어 및 프로그램을 실행할 수 있는 공간 => 운영체제와 사용자가 운영체제와 의사소통을 하게 도와줌.

  <br>

- #### Ubuntu에서 기본으로 사용되는 shell => Bash ( Bourne Again shell)

  - `$ cat /etc/shells` => 설치되어 있는 shell을 확인하는 명령어

  <br>

- #### Prompt(프롬프트)란? 

  > 사용자와 interactive한 입력을 주고 받을 수 있는 명령 대기 표시자  =>  즉, 쉽게 말해 명령어 한 줄을 칠 수 있는 표시
  >
  > - Bash의 경우 `$`, Cshell의 경우 `%` 이다.
  > - `$ echo $PS1`  => 프롬포트에 띄워지는 정보에 대한 양식을 확인할 수 있는 명령어
  >   - Output :  `\[\e]0;\u@\h: \w\a\]${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$` 
  >   - `\u` : username
  >   - `\h` : hostname
  >   - `\w` : current directory
  >   - ... 등 다양하다.
  >   -  `$ PS1="\u@\h:\w$"` => 해당 명령어를 통해 PS1 환경 변수에 기록해 변경가능하다.

  <br>

- ####  Bash의 기본명령어

  - `$ echo [OPTION]... [STRING]...` : 화면에 글자를 출력

    - OPTION
      - `-n` : 뉴라인 제외
      - `-e` : Escape 코드 지원
      - `-E` : Escape 모드 미지원 (default)

  - Redirection (`>`, `>>`, `2>`,`2>&`) : 결과물을 다른 장치로 보낼 수 있는 명령어

    - `>` : Output
      - `$ echo "Hello" > hello.txt` => 파일에 string을 출력, 기존 파일을 덮어쓴다.
      - `$ ls > file.txt` => ls의 결과를 file.txt에 출력
    - `>>` : Append
      - `$ echo "Hello HaHa" >> hello.txt` => 기존 파일에 누적시켜 출력
    - `2>` : Error
      - `$ aaa 2> file.txt` => 실패한 결과물을 파일에 출력

    > 참고 : 출력 장치의 유형 
    >
    > - 장치 번호 1 : stdout (표준 출력)
    > - 장치 번호 2 : stderr (에러 출력)
    > - 장치 번호 3 : stdin (표준 입력)

  - Delimiter 

    - `<<` : 뒤에 오는 값이 들올때까지 입력을 받음

      - `$ cat << end` : end값이 입력될때까지 입력을 받고 end가 들어오면 입력되었던 값들을 출력

      - `$ cat << end > hello.txt` : 위와 같이 동작하지만 표준출력이 아니고 파일에 출력 

  - Pipe

    - `|` : 왼쪽의 출력 값을 프로세스간에 전달을 해줌
      - `$ ls -l | grep hello` => ls의 결과값에서 검색
      - `$ ls -l wc -l` => ls의 결과값의 줄 개수 확인
      - `$ ls -l | grep hello | wc -l` => (다중 파이프) ls의 결과값에서 검색된 결과의 줄 개수 확이
      - `cat hello.txt | more` => 출력값 내에서 페이징 처리

  - 명령어 history

    - `history [OPTION]` : 쉘에서 입력한 명령어들의 기록을 보여줌

      - `$ history 10` : 최근 10개의 히스토리 보기
      - `$ history -c`: 히스토리 버퍼 삭제
      - `$ !15` : history의 결과값에서 15번째 라인을 다시 실행
      - `$ !! ` : 바로 이전 명령어를 다시 실행

      <br>

- #### 환경변수 
  - 환경변수 확인

    - `$ printenv` , `$ env` : 전체 환경변수 확인
    - `$ echo $환경변수` : 해당 환경변수 확인

  - 환경변수 변경

    - `$ 환경변수=값` : 환경변수 변경 (해당 터미널에서만)
    - `$ export 환경변수=값` : 환경변수 변경 (전체 터미널에서)

  - 주요 환경변수

    - PATH : 명령어를 실행하는 디렉토리 정보

      - `$ echo $PATH` => PATH 정보 확인
      - `$ export PATH=$PATH:<추가할 디렉토리>` => PATH에 디렉토리 정보 추가

      ```
       - PATH를 확인하는 과정
      	1. PATH 디렉토리 확인
      	2. 실행권한 확인
      		2.1 SetUID 확인
      	3. 명령어를 해당 사용자ID로 실행
      		3.1 해당 명령어의 소유주 권한으로 명령어 실행
      ```

      - `$ which [FILENAME]` : 실행하고자하는 바이너리 파일이 어디에서 실행되는지 확인
        - `$ which ls`, `$ which python`

    - LANGUAGE / LANG : 언어 및 언어셋

      - `$ LANGUAGE=en COMMAND [ARGS]...` => 언어를 (한시적으로) 변경
      - `$ LANG=c COMMAND [ARGS]...` => 언어셋을 (한시적으로) 변경
      - `$ exprot` => 영구적으로 변경
      - Locale : 언어와 언어셋(캐릭터셋) 그리고 다양한 지역 설정값
        - `$ locale` => 현재 로케일 정보 확인
        - `$ locale -a` => 설정 가능한 모든 로케일 정보 확인

  - [참고자료] : [http://blog.naver.com/PostView.nhn?blogId=koromoon&logNo=220793570727&redirect=Dlog&widgetTypeCall=true](http://blog.naver.com/PostView.nhn?blogId=koromoon&logNo=220793570727&redirect=Dlog&widgetTypeCall=true)

    <br>

- #### alias

  - alias란?

    > bash쉘의 장점으로 자주 쓰는 긴 명령어를 짧게 요약해 사용할 수 있는 일종의 별칭
    >
    > - 이미 `ls` 도 `ls --color=auto`라는 명령어가 축약되어 있다.

  - ex)

    - `$ alias ..="cd .."`
    - `$ alias ...="ce ../.."`

<br>

- #### Shell Booting Sequence

  - ```
    /etc/profile => (공통수행)환경 설정들
    /etc/profile.d/*.sh => (공통수행)
    /etc/bash.bahsrc => (공통수행)시스템 alias 등
    ~/.profile => (사용자별 디렉토리)시작 프로그램 등
    ~/.bashrc => (사용자별 디렉토리) alias 등
    ~/.bahs_aliases => (이 파일이 추가적으로 있다면 수행 - 기본적으로 없음)
    ```

  - BASH의 종료
    - `~/.bash_logout` 의 실행

