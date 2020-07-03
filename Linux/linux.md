#### VirtualBox 네트워크
	- NAT : 가상머신 내부 네트워크에서 Host PC 외부 네트워크 단방향 연결 (Host 내부 네트워크와 통신 불가)
	- 어댑터에 브리지 : 호스트 PC와 동등하게 외부 네트워크와 연결 (IP할당 외부로부터 받음)
	- 내부 네트워크 : Host 내부 네트워크와만 통신 가능
	- 호스트 전용 어댑터 : Host와 내부 네트워크와만 통신 가능 (외부 네트워크와 단절)
	- 일반 드라이버 : 거의 미사용 (UDP 터널 네트워크 등)
	- NAT 네트워크 : NAT + Host 내부 네트워크와 통신 가능
	- 연결되지 않음 : 네트워크 미사용 (Link Down)

#### 운영체제의 부팅 과정
	- BIOS : POST (Power On Self Test) 및 불리적 Boot 디바이스 선정
	- MBR : Master Boot Record로 HDD의 특정섹터 ( 0번 섹터 512Bytes)
	- GRUB : 부트로더(Bootloader)로 소프트웨어 영역 (멀티 부트 등 처리를 위한 멀티 스테이지 부트)
	- Kernel : 커널이 운영체제 소프트웨어 메모리에 올라가 구동
		- `/sbin/init`을 실행하며 `initrd`패키지의 실행 (pid 1)
	- Init : 부팅 (Init process) 루트 유저 프로세스로 systemd 등의 부팅 과정 수행
		- `/etc/inittab` 등 실행
	- Runlevel : 소프트웨어 부트 스크립트
		- `/etc/rc.d/rc*.d/`

#### 표준 파일 시스템(FHF) 및 디렉토리 구조
``` 
/ : 루트 디렉토리
/bin : 기본적인 명령어
/boot : 부트로더 디렉토리
/dev : 시스템 디바이스 (장치파일)
/etc : 각종 설정파일
/home : 사용자의 홈 디렉토리
/lib : 라이브러리 (및 커널모듈)
/media : 외부저장소 (cdrom, usb 등)
/mnt : 외부 저장소 또는 파일시스템 마운트 포인트
/proc : 시스템 설정들의 가상파일 시스템 (pseudo-file시스템)
/root : 루트 사용자의 홈 디렉토리
/sbin : 시스템 (관리자용) 명령어
/tmp : 임시 저장소
/usr : 일반 사용자들 공통파일
/var : 시스템 운용중에 생성되는 임시 데이터 저장소
```
