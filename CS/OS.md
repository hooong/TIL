# OS
### 프로세스 (process)
- 실행 중인 프로그램으로 디스크로부터 메모리에 적재되어 cpu의 할당을 받을 수 있는 것.
- 메모리에서 프로세스의 구조
	- code : text section으로 실행될 코드가 담김. (ProgramCounter)
	- data : 전역변수가 담김.
	- heap : 런타임시에 동적 할당이 되는 것이 담김.
	- stack : 함수, 지역변수, return add, StackPointer를 가짐
- 각각의 프로세스는 고유한 address space를 가지며 다른 프로세스가 침범하지 못한다.
- fork()가 되면 부모의 address space를 복제한다.
- zombie process : 자식 프로세스가 죽었지만 부모가 수습해주지 못해 종료되지 못하는 프로세스, 부모에서 wait()이 불리지 않아 process table에서 사라지지 않음. 
	- 메모리 낭비가 될 수 있다. 되도록 부모 프로세스는 wait()을 사용해 자식 프로세스의 종료 상태를 읽어들여야 함.
- orphan process : 부모 프로세스가 자식 프로세스보다 먼저 종료되면 init 프로세스(PID:1)가 해당 프로세스의 부모 프로세스가 됨.
	- init 프로세스 : 유닉스 계열의 운영체제에서 부팅 과정 중 생성되는 최초의 프로세스이며 시스템이 종료될때까지 계속 살아있는 데몬

### 프로세스 제어 블록 (Process Control Block, PCB)
- 특정 프로세스에 대한 중요한 정보를 저장하고 있는 운영체제의 자료구조
- 프로세스별 고유한 정보를 가짐.
- PCB의 구조
	- PID : 프로세스 식별번호
	- 프로세스 상태 : new, ready, running, waiting, terminated
	- 프로그램 카운터(PC) : 프로세스가 다음에 실행할 명령어의 주소
	- CPU 레지스터
	- CPU 스케쥴링 정보 : 프로세스의 우선순위, 스케쥴 큐에 대한 포인터 등
	- 메모리 관리 정보 : 페이지 테이블 또는 세그먼트 테이블 등과 같은 정보
	- 입출력 상태 정보 : 프로세스에 할당된 입출력 장치들과 열린 파일 목록
	- 어카운팅 정보 : 사용된 CPU시간, 시간제한, 계정번호 등

### 스레드 (Thread) 
- 프로세스의 실행 단위
- 스레드는 독립적인 스레드ID, PC, 레지스터 집합, 스택으로 구성
- 동일한 프로세스의 스레드들은 코드, 데이터 섹션, 열린 파일이나 신호와 같은 운영체제 자원들을 공유
- Q)스레드마다 독립적인 스택을 가지는 이유는?
	- 함수 호출시 전달되는 인자, return address, 함수내의 지역 변수 등을 저장하는 스택을 독립적으로 가져 독립적인 함수 호출을 가능하게하고 스레드의 정의에 따라 독립적인 실행 흐름을 추가할 수 있다.
- Q)스레드마다 독립적으로 PC가 할당되는 이유는?
	- 스레드는 CPU를 할당받았다가 스케줄러에 의해 다시 선점당한다. 따라서 명령어가 연속적으로 수행되지 못하기때문에 어디까지 실행되었는지 기억해야할 필요가 있기때문. 

### 멀티 스레드
- 장점
	- 코드와 힙과 같은 자원을 공유하기때문에 메모리 공간, 시스템 자원 소모가 줄어든다. 
	- 스레드 간의 통신을 전역변수 또는 힙 영역을 이용해 프로세스 간의 통신보다 간단하다.
	- 프로세스 context switch와 달리 캐시 메모리를 비울 필요가 없기때문에 더 빠름 -> 시스템의 throughtput이 향상, 자원소모 감소, 프로그램 응답시간 단축
- 단점
	- coherency : 공유하는 영역때문에 사용중인 변수나 자료구조에 접근하여 엉뚱한 값을 읽어오거나 수정할 위험 존재.
	- 위의 문제를 해결하기위해서는 동기화 작업(작업 처리 순서 컨트롤, 공 자원에 대한 접근 컨트롤)이 필요한데, 이로 인한 병목현상이 발생하여 성능 저하 가능성이 높음. -> 과도한 락으로 인한 병목현상을 줄여야함.

### 멀티 프로세스
- 멀티 스레드의 경우 오류로 인해 하나의 스레드가 종료되면 전체 스레드가 종료되고 동기화 문제를 안고있지만 멀티 프로세스의 경우 하나의 프로세스가 죽어도 다른 프로세스에 영향을 끼치지 않는 장점이 있다.
- 멀티 스레드와 멀티 프로세스는 trade-off 관계로 볼 수 있음.

### 프로세스 스케줄러
프로세스를 스케줄링하기 위한 Queue
- Job Queue : 현재 시스템 내에 있는 모든 프로세스의 집합
- Ready Queue : 현재 메모리 내에 올라와 있어 CPU를 잡아 실행을 기다리는 프로세스의 집합
- Device Queue : I/O 작업을 대기하고 있는 프로세스의 집합

Queue에 프로세스를 넣고 빼는 스케줄러
- Long-term scheduler (or job scheduler)
	- 메모리-디스크 사이의 스케줄링
	- 한정된 메모리때문에 디스크에 임시로 저장되어있는 프로세스 중 어떤 프로세스를 ready queue로 보낼지를 결정
	- degree of Multiprogramming을 제어
	- new -> ready
- Short-term scheduler (or CPU scheduler or Dispacher)
	- CPU-메모리 사이의 스케줄링
	- Ready Queue에 존재하는 프로세스중 어떤 프로세스를 running 상태로 바꿀지를 결정
	- ready -> running -> waiting -> ready
- Medium-term scheduler (or Swapper)
	- degree of Multiprogramming을 줄이기 위한 스케줄링
	- 여유 공간 마련을 위해 프로세스 통째로 메모리에서 지우고, 디스크에 저장 (swapping)
	- ready -> suspended
		- suspended 상태는 외부적인 이유로 프로세스의 수행이 정지된 상태로 메모리에서 내려간 상태. 즉, 프로세스 전부 디스크로 swap out. 이 상태에서는 ready state로 스스로 돌아갈 수 없음.

### CPU 스케줄러
Ready Queue의 프로세스들을 스케줄링

##### FCFS (First Come First Served)
- 전형적인 FIFO 구조
- Non-Preemptive
	- CPU가 한번 점유되면 작업이 완료될때까지 반환하지 않는 방식
문제점
- convoy effect 
	- 오래걸리는 작업이 앞에 올 경우 turnaround time이 커져 효율성이 낮아지는 현상 발생

##### SJF (Shortest Job First)
- CPU burst가 가장 작은 것부터 실행
- 평균 waiting time이 가장 작다.
- Non-preemptive
문제점
- 현재 이후 시점에서 추가되는 CPU burst의 크기를 모두 알 수 없다.

##### SRTF (Shortest Remaining Time First)
- SJF의 preemptive 버전
- 새로운 job이 들어올때마다 모든 job의 remaining time을 확인해서 더 작은 것에게 CPU를 할당
문제점
- starvation

##### Priority Scheduling
- 우선순위가 높은 job이 우선적으로 CPU를 할당.
- Preemptive : 더 높은 우선순위의 job이 도착하면 CPU를 재할당
- Non-preemptive : 더 높은 우선순위의 job이 도착하면 Ready Queue의 앞에 넣는다.
문제점
- starvation
	- 우선순위가 높은 job이 계속 도착하는 경우 낮은 우선순위가 실행되지 못함.
	- -> 해결책 : aging 또는 priority boosting으로 오래 기다린 낮은 프로세스의 우선순위를 높여주는 방법

##### RR (Round Robin)
- Circular FIFO queue로 동일한 크기의 할당 시간(scheduling quantum)동안만 CPU를 할당
- timle slice이 너무 작다면 context switch가 overhead가 될 수 있고, 너무 크다면 일반 FIFO와 다를게 없다.
- Preemptive
장점
- No starvation
- Response time이 빨라짐.

##### Multilevel Queue
- 프로세스의 우선순위별로 여러개의 Queue로 스케줄링
- Queue마다 다른 스케줄링 기법을 적용
- 각 Queue마다 확률에 따라 CPU를 할당
문제점
- starvation

##### MLFQ (Multi-Level Feedback Queue)
- Multilevel Queue의 flexible버전
- 처음들어오는 job을 우선순위가 가장 높은 queue에 넣고 time quantum이 길면 낮은 순위의 queue로 옮겨가는 방식
장점
- 유연성이 뛰어남
- response time이 짧다.
문제점
- starvation -> aging 방식으로 낮은 우선순위 queue에서 오래 기다린 job을 높은 우선순위 큐에 넣는다.
- 구현이 복잡

### 동기 VS 비동기
##### 동기 (Sync)
- 실행되었을 때 값이 반환되기 전까지 blocking이 되어있다는 의미
##### 비동기 (Async)
- blocking되지 않고 이벤트 queue나 백그라운드 스레드에게 위임을 하고 바로 다음 코드를 실행한다는 의미

### Synchronization

#### Critical Section
- 동일한 자원을 동시에 접근하여 실행되는 코드 영역을 말함.
- 공유되는 자원에서 race condition이 발생할 수 있다.

해결책
- spinLock
	- Critical section에 진입하면서 lock을 획득하고 빠져나올 때 lock을 방출함으로써 프로세스들이 동시 접근을 막음.
조건
	- correctness
		- Mutual exclusion : 오직 한 스레드만이 Critical Section에 접근 가능
		- progress : Critical Section에서 실행중인 프로세스가 없고 별도의 동작이 없는 프로세스들만 Critical Section 진입 후보가 될 수 있다.
		- Bounded waiting : 하나의 프로세스가 영원히 기다리게 하면 안된다.
	- Fairness
		- 모든 스레드들은 lock을 가질 공정한 기회를 가져야함.
문제점
	- busy Waiting : burn해서 많은 CPU 자원을 잡아먹음

- Semaphores
	- 소프트웨어상에서 Critical Section 문제를 해결하기 위한 동기화 도구
	- Binary semaphore(=mutex) : 0과 1사이의 값만 가능하여 하나의 프로세스만 접근 가능
	- Counting semaphore : N개까지 접근이 가능하게끔 자원을 사용하면 세마포어가 감소하고 방출하면 세마포어가 증가.
대표적인 사용
	- Readers/Writers
		- readcount(int), mutex(semaphore), rw_mutex(semaphore)
		- 만약 read가 계속 들어오면 write가 계속 기다려야되는 starvation문제가 있을 수 있다.
	- Bounded Buffer
		- mutex(Binary semaphore), empty(counting semaphore,init=N), full(counting semaphore,init=0)
	- Dining Philosopher
		- Deadlock발생가능성
		- 위의 4가지 조건 중 한가지를 없애주는 방법
		- ex) 왼쪽 포크를 들고 오른쪽을 들수없으면 왼쪽 포크를 내려놓는다.
		- 또는 포크에 우선순위를 매긴다.
	
- 모니터
	- 고급언어가 제공하는 abstract 데이터 타입이다.


#CS

