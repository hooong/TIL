#### DevOps 

> 2008년 애자일 컨퍼런스에서 앤드루 클레이 쉐이퍼와 패트릭 드부와가 "애자일 인프라스트럭처"에 대해 논의하며 처음으로 사용.
>
> - 소통, 협업, 통합 및 자동화를 강조하는 소프트웨어 개발 방법론
> - 개발과 운영이 상호의존적으로 대응해야 한다는 의미
> - 개발과 운영 사이의 역할



#### AWS

> 클라우드 서버 구축 서비스

- 장점 
  - 탄력적인 웹 규모 컴퓨팅
  - 다양한 Command(API) 제공
  - 유연한 클라우드 호스팅 서비스
  - 통합 (storage, RDS 등 통합이 용이)
  - 안정성
  - 보안
- 단점
  - 베어 메탈 성능을 원할때 
  - 플랫폼보다는 솔루션에 적합
  - 가격



#### Docker

> 리눅스의 응용 프로그램들을 소프트웨어 컨테이너 안에 배치시키는 일을 자동화하는 오픈 소스 프로젝트

- 장점
  - 실행시점에 상관없이 구성 시점을 고를 수 있음
    - 버전 관리 차원에서 동시에 배포가 가능 (이미지화)
    - 눈송이 서버 : 서버가 많아질때 각 서버별로 버전이 상이함 => 이미지를 똑같이 컨테이너에 배포하므로 문제를 줄일 수 있음.
  - 개발 프로그램 설치와 삭제가 용이
    - Java, mysql, oracle, elk, nginx등 서버 프로그램 설치와 삭제가 용이
  - 운영체제 도커 실행 소스 일관성, 유연성
    - 초기 인프라 환경 설정은 복잡하고 어려우나, 쉽고 일관성 있게 만들어준다.
  - 이미지 용량이 크게 줄어듬
    - 리눅스 컨테이너 사용
  - 여러군데 배포할 수 있는 확장성
    - Github과 비슷한 느낌으로 Push/Pull이 용이



#### Jira

> [ATLASSIAN](https://www.atlassian.com/ko)의 협업 툴

- 설치하기 (Docker 사용)

  1. AWS의 EC2 (Amazon Linux2 AMI 기준) 접속

  2. Docker가 설치되어있는지 확인

     - `$ docker`  => `docker: command not found` 일 경우 설치 안됨.

  3. Docker 설치 및 데몬 실행

     - `$ sudo yum install docker-io -- yes`  => yum을 통해 설치

     - `$ sudo systemctl start docker` => 데몬 실행

     - `$ sudo setfacl -m user:ec2-user:rw /var/run/docker.sock` => 현재 사용중인 ec2-user에 docker 사용 권한 부여

     - `$ docker ps -a`  => docker list 정상 작동 확인

       ```
       CONTAINER ID   IMAGE   COMMAND ... ( 생략 )
       ```

  4. Jira 설치

     - 기존 지라 도커 컨테이너 삭제

       `docker rm --volumes --force "jira-container"` => 기존에 있다면 삭제...

     - 지라 도커 컨테이너 설치 (dockerhub pull)

       `docker pull cptactionhank/atlassian-jira-software:latest`

     - 지라 도커 컨테이너 생성

       ```
       docker create --restart=no --name "jira-container" \
         --publish "8080:8080" \
         --volume "hostpath:/var/atlassian/jira" \
         --env "CATALINA_OPTS= -Xms1024m -Xmx1024m -Datlassian.plugins.enable.wait=300" \
         cptactionhank/atlassian-jira-software:latest
       ```

     - 지라 도커 컨테이너 실행

       `docker start --attach "jira-container"`

     - `"EC2의 퍼블릭 IP":8080`로 접속해 `Jira setup` 창이 뜨는지 확인

     - `Set it up for me` 선택 => `jira Software (server)`  선택 => `Generate License` 클릭 => Confirmation창 `yes` 클릭
     - email, username, pw 입력 후 `Next` 클릭 => 설치 완료
     - 언어 및 아바타 선택 => 설정 완료

     