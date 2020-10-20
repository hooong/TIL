# Django
#### CGI (Common Gateway Interface)
웹서버에서 정적인 컨텐츠가 아닌 동적인 컨텐츠를 사용자에게 제공하기 위한 인터페이스
- ex) 웹페이지 로그인하는 것.
- 한계점
	- 요청마다 프로세스를 생성하여 성능과 확장성이 좋지 않음 -> fastCGI (하나의 프로세스에서 여러 요청을 처리)

#### WSGI (Web Server Gateway Interface)
- Web Server가 Django에게 말을 걸 수 있는 수단으로 생각할 수 있다.
- 웹 서버가 Python 프로그램으로 python을 쓰기 힘들다는 점에서 등장
- 웹 서버와 python application간의 통신 방식에 존재하는 종속성을 없애기 위해 표준으로 정의한 것 
- 대표적인 WSGI 서버 uwsgi, gunicorn이 있음.
#CS