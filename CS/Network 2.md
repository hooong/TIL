# Network

Tcp 흐름, 혼잡, 에러제어

## DNS
네트워크상에서 컴퓨터를 식별하는 호스트명

## DNS Server
IP주소와 도메인의 매핑 정보를 관리하면서 도메인 or IP주소를 묻는 요청이 올 경우 이에 응답하는 서버.
- 모든 인터넷 트래픽을 한 곳에서 담당할 수 없어 계층 형태로 분산시킴
	- 루트 DNS 서버
	- 최상위 레벨 도메인 DNS 서버 (TLD)
	- 책임 DNS 서버
- Recursive Queries
	1. 로컬 DNS 서버로 쿼리를 보냄 -> cache를 살펴보고 없다면 다음 단계로 진행
	2. Root Name server로 쿼리를 보냄 -> TLD 서버 정보 제공
	3. TLD name server로 쿼리를 보냄
	4. 여러번의 질의를 통해 타겟 ip 주소를 알아냄 -> 접속

## DHCP
호스트의 IP주소 및 TCP/IP설정을 클라이언트에 자동으로 제공하는 프로토콜
- 자신의 IP주소, 가장 가까운 라우터의 IP주소, 가장 가까운 DNS서버의 IP주소를 받음.
- 이후 ARP 프로토콜을 이용해 IP주소를 기반으로 가장 가까운 라우터의 MAC주소를 알아냄.


웹 통신의 큰 흐름
3way-Hand-shake
Quic
Ns-3

#CS