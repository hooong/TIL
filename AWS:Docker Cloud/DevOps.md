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

     <br>

- Jira API

  > API (Application Programming Interface)란? - 기능 사용할 수 있도록 만들어 놓은 일종의 함수와 같은 것. 
  >
  > Jira는 API를 통해 이슈를 관리할 수 있도록 하는 API를 제공한다.

  - ISSUE 관련 API

    ```
    api/2/issue
    
    Create issue  POST /rest/api/2/issue
    Create issues POST /rest/api/2/issue/bulk
    Get issue 		GET /rest/api/2/issue/{issueIdOrKey}
    Delete issue  DELETE /rest/api/2/issue/{issueIdOrKey}
    Edit issue		PUT /rest/api/2/issue/{issueIdOrKey}
    ```

    - [참고] [Jira API Docs](https://docs.atlassian.com/software/jira/docs/api/REST/8.4.2/)

    <br>

  - Postman으로 issue GET 해보기

    - Request 설정

      `GET http://<AWS EC2 IP 주소>:8080/rest/api/2/issue/BLOG-1` => 'BLOG-1'은 이슈의 이름

    - Auth 설정

      - Type : Basic Auth => username, pw 입력

    - Header 설정

      - Content-Type : application/json

    - Response 확인

  <br>

  - Postman으로 issue Create 해보기

    - Request 설정

      `POST http://<AWS EC2 IP 주소>:8080/rest/api/2/issue`

    - Auth와 Header는 GET과 동일

    - Body 설정 (json)

      ```json
      {
          "fields": {
              "project": {
                  "key": "BLOG"
              },
              "summary": "테스트",
              "issuetype": {
                  "id": "10002"
              },
              "assignee": {
                  "name": "hooong"
              },
              "reporter": {
                  "name": "hooong"
              }
          }
      }
      ```

    - Response 확인 및 web page에서 확인

    <br>

  - Postman으로 이슈 상태 확인하기

    - Request 설정

      `GET http://<AWS EC2 IP 주소>:8080/rest/api/2/issue/WF-1/transitions`

    - Auth와 Header는 GET과 동일

    - Response

      각 상태의 id값을 확인할 수 있다.

      ```json
      {
          "expand": "transitions",
          "transitions": [
              {
                  "id": "121",
                  "name": "Canceled",
                  "to": {
                      "self": "http://54.180.141.198:8080/rest/api/2/status/10004",
                      "description": "",
                      "iconUrl": "http://54.180.141.198:8080/images/icons/statuses/generic.png",
                      "name": "Canceled",
                      "id": "10004",
                      "statusCategory": {
                          "self": "http://54.180.141.198:8080/rest/api/2/statuscategory/2",
                          "id": 2,
                          "key": "new",
                          "colorName": "blue-gray",
                          "name": "할 일"
                      }
                  }
              }
          ]
      }
      ```

<br>

- Jira 관리자
  - 관리자 페이지 (이슈)
    - 이슈 유형 - 이슈를 관리한다 (새로운 이슈 타입 생성)
    - 이슈 유형 계획 - 프로젝트에 어떤 이슈 타입을 넣을 수 있을지를 설정
    - 업무 흐름 - 업무의 단계를 설정
    - 업무 흐름 계획 - 이슈 유형과 업무 흐름을 매핑
    - 화면 - 편집을 통해서 화면에 보여줄 필드 정보를 추가/수정
    - 화면 계획 - 커스터마이즈한 화면을 특정 화면 계획과 연결
    - 이슈 유형 화면 계획 - 이슈 유형과 화면 계획을 연결
    - 사용자 정의 필드 추가 - 사용자가 원하는 형태의 필드를 구성

- Jira CustomField

  > 이슈 등록에 필요한 항목을 필요에 따라 커스텀하여 생성할 수 있다.

  1. 사용자정의 필드에서 필드 추가

  2. 화면 추가 (요약, 보고자, 기한 세 가지 항목 필수)

  3. 화면 계획 추가

  4. 이슈 유형 화면 계획 추가

