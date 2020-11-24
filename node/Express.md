# Express

### 내장 모듈을 사용해 웹 서버를 띄워보기

- `index.js` 작성

  ```js
  const http = require('http');
  
  // 서버 생성
  http.createServer( (request, response) => {  
      response.writeHead(200, {'Content-Type' : 'text/plain'});
      response.write('Hello Server');
      response.end();
  }).listen(3000);
  ```

  - `$ nodemon index.js`
  - 브라우저를 통해 `localhost:3000`으로 접속 후 "Hello Server"가 출력되는 것을 확인할 수 있다.



### Express란?

> Node.js의 핵심 모듈인 http와 Connect 컴포넌트를 기반으로 하는 웹 프레임워크

- 장점
  - 커뮤니티가 가장 커서 정보를 얻기 쉽다.
  - 서버를 쉽게 실행하고 운영을 할 수 있게 해준다.
- 단점
  - 수작업이 많고 문제에 대한 접근법이 여러가지이다.
  - 메모리를 많이 먹는다.
  - async/await를 정식지원하지 않는다.



### Express를 사용해 웹 서버를 띄워보기

- 프로젝트 폴더 생성 후 init

  - `$ npm init -y`

- `express` 모듈 설치

  - `$ npm install express`

- `app.js` 생성 후 코드 작성

  ```js
  // 모듈 불러오기
  const express = require('express');
  
  // express 생성
  const app = express();
  const port = 3000;
  
  // get 요청 구현
  app.get('/', (req, res) => {
      res.send('hello express');
  });
  
  // 위의 createServer와 같은 역할
  app.listen(port, () => {
      console.log('Express listening on port', port);
  })
  ```



### Route 나누기

- `routes` 폴더 생성

- 폴더 안에 `admin.js` 생성 후 아래 내용 작성

  ```js
  const express = require('express');
  // express의 Router() 생성
  const router = express.Router();
  
  // GET /admin
  router.get('/', (req, res) => {
      res.send('admin 이후 url');
  });
  
  // GET /admin/products
  router.get('/products', (req, res) => {
      res.send('admin products');
  });
  
  // 모듈 내보내기
  module.exports = router;
  ```

- 위에서 서버를 열었던 `app.js` 파일에서 admin route 모듈 사용

  ```js
  const express = require('express');
  
  // admin 라우트 모듈 받아오기
  const admin = require('./routes/admin');
  
  const app = express();
  const port = 3000;
  
  app.get('/', (req, res) => {
      res.send('express start');
  });
  
  // admin route 사용
  app.use('/admin', admin);
  
  app.listen(port, () => {
      console.log('Express listening on port', port);
  });
  ```

- 브라우저로 `localhost:3000/admin`과 `localhost:3000/admin/products`로 접속해 확인



### View Engine (nunjucks)

> express의 view engine에는 jade, has 등 많지만 여기서는 nunjucks를 사용해본다.

- nunjucks 설치하기

  `$ npm i nunjucks` (i는 install과 같이 동작한다.)

- 최상위 폴더에 `template` 폴더 생성

- `template` 하위에 admin router에서 사용할  `admin`폴더 생성

- `products.html` 파일 생성

  ```html
  products {{ message }}
  <!-- '{{}}'는 백엔드에서 전달해주는 값을 사용할 수 있는 문법 -->
  ```

- `app.js`에 nunjucks 셋팅

  ```js
  const express = require('express');
  
  // nunjucks import
  const nunjucks = require('nunjucks');
  
  // ... 생략
  
  const app = express();
  const port = 3000;
  
  // nunjucks 설정 
  // => 'template'폴더에서 가져오고 express로 사용할 app 변수 설정
  // => autoescape: html을 render하는 과정에서 태그가 작용하는 것을 방지하기 위한 조건
  // => html에서 {{ message | safe }}로 작성 시 html 태그 동작
  nunjucks.configure('template', {
      autoescape : true,
      express : app
  });
  
  // ... 생략
  ```

- `admin.js`에서 routing에 대한 respond 수정하기

  ```js
  router.get('/products', (req, res) => {
      // render라는 함수로 'template'이후의 경로를 써주고 html에서 사용할 message에 대한 데이터 설정
      res.render('admin/products.html', {
          message : 'hello!!!!'
      });
  });
  ```

- 브라우저에서 `localhost:3000/admin/products`에서 html 파일이 잘 뜨는지 확인.

  #### [참고] 

  - nodemon이 html 변경도 감지하게끔 하는 방법

    ```json
    ...
    
    "scripts": {
        "start": "npx nodemon -e js,html app.js"
      },
    
    ...
    ```

    - `-e js,html'` 옵션을 추가해주면 재시작을 하지 않아도 html 파일 변경도 적용이 된다.



### nunjucks에서의 템플릿 상속

- `template/layout`에 `base.html` 생성 후 작성

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>{{ title }}</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  </head>
  <body>
      <div class="container" style="padding-top:100px;">
  
          {% block content %}{% endblock %}
  
      </div>    
  </body>
  </html> 
  ```

  - `{% block content %} {% endblock %}` 부분을 block로 지정

  

- 기존 `products.html` 파일 내용 수정

  ```html
  {% set title = "관리자 리스트" %}
  {% extends "layout/base.html" %}
  
  {% block content -%}
      <table class="table table-bordered table-hover">
          <tr>
              <th>제목</th>
              <th>작성일</th>
              <th>삭제</th>
          </tr>
          <tr>
              <td>제품 이름</td>
              <td>
                  2020-03-07
              </td>
              <td>
                  <a href="#" class="btn btn-danger">삭제</a>
              </td>
          </tr>
      </table>
  
      <a href="/admin/products/write" class="btn btn-default">작성하기</a>
  
  {% endblock %} 
  ```

  - `{% extends "layout/base.html" %}` 를 사용해 템플릿을 상속받고 `{% block content -%}{% endblock %} `안의 내용이 `base.html`의 block부분에 추가되어 rendering이 된다.



### 미들웨어

> Express에서 request와 response 사이에서 작동하는 코드를 미들웨어라고 한다.

- `morgan ` 설치하기

  - `$ npm i morgan`

- `app.js`에서 `morgan` 사용하기

  ```js
  // ... 생략
  const logger = require('morgan');
  
  // ... 생략
  
  // 미들웨어 셋팅
  app.use(logger('dev'));
  
  // ... 생략
  ```

- 이후 브라우저에서 요청을 보내면 아래와 같이 log를 확인할 수 있다.

  ` GET /admin 304 3.126 ms - -`

- 잘 작동한다면 이제 `admin.js`에 먼저 미들웨어를 만들고 적용을 시켜본다.

  ```js
  // ... 생략
  
  // 미들웨어 함수 등록 => 세 가지의 인자를 받는다.
  function testMiddleware(req, res, next) {
      console.log("첫번째 미들웨어");
      next();
  }
  
  // 또 다른 미들웨어 함수 등록
  function testMiddleware2(req, res, next) {
      console.log("두번째 미들웨어");
      next();
  }
  
  // '/admin' 요청에 대하여 미들웨어를 두 가지 모두 적용
  router.get('/', testMiddleware, testMiddleware2, (req, res) => {
      res.send('admin 이후 url');
  });
  
  // ... 생략
  ```

  - `localhost:3000/admin`을 요청해보면 console을 확인해보면 아래와 같다.

    ```
    첫번째 미들웨어
    두번째 미들웨어
    GET /admin 304 2.460 ms - -
    ```

- 이번에는 `app.js`에서 미들웨어를 등록해본다.

  ```js
  // ... 생략
  
  // 미들웨어 함수 등록
  function vipMiddleware(req, res, next) {
      console.log('최우선 미들웨서');
      next();
  }
  
  // `/admin`으로 시작하는 url을 요청받으면 vipMiddleware를 모두 제일 먼저 거치게 된다.
  app.use('/admin', vipMiddleware, admin);
  
  // ... 생략
  ```

  - 이후 `localhost:3000/admin` 요청을 보내보면 아래와 같다.

    ```
    최우선 미들웨서
    첫번째 미들웨어
    두번째 미들웨어
    GET /admin 304 3.126 ms - -
    ```

- 이렇게 미들웨어를 설정해서 요청에 대한 로그를 확인할 수도 있고, 요청을 처리하기 전에 선행작업을 해줄 수도 있다.

- 로그인이 되어있는지 아닌지를 확인하는 미들웨어를 예로들 수 있다.

  ```js
  function loginRequired(req,res,nest) {
      if (로그인이 되어있지 않으면) {
          res.redirect(로그인 창으로);
      } else{
          next();
      }
  }
  ```

  

### form (body-parser)

> Body-parser는 미들웨어로 동작하여 form에서 들어오는 body에 있는 데이터를 읽어주는 역할을 한다.

- `app.js`에 `body-parser`불러오고 셋팅하기

  - `body-parser`는 express에 내장되어 있어 따로 설치해주지 않아도 된다.

  ```js
  // ... 생략
  const bodyParser = require('body-parser');
  
  // ... 생략
  
  // 미들웨어 셋팅
  app.use(logger('dev'));
  app.use(bodyParser.json());
  app.use(bodyParser.urlencoded({extended : false}));
  
  // ... 생략
  ```

- `template/admin`에 `write.html`생성 후 작성

  ```html
  {% set title = "제품 등록" %}
  {% extends "layout/base.html" %}
  
  {% block content -%}
      <form action="" method="post">
          <table class="table table-bordered">
              <tr>
                  <th>제품명</th>
                  <td><input type="text" name="name" class="form-control"/></td>
              </tr>
              <tr>
                  <th>가격</th>
                  <td><input type="text" name="price" class="form-control"/></td>
              </tr>
              <tr>
                  <th>설명</th>
                  <td><input type="text" name="description" class="form-control"/></td>
              </tr>
          </table>
          <button class="btn btn-primary">작성하기</button>
      </form>
  
  {% endblock %} 
  ```

  > `action`이 비어있으면 현재 GET으로 들어온 url 그대로  Method만 POST 형식으로 바꾸어 다시 요청을 보내준다.

- `admin.js`에 url에 해당하는 POST를 라우팅해준다.

  ```js
  // ... 생략
  
  router.post('/products/write', (req,res) => {
    // body-parser를 설정해주었기때문에 req에서 body를 통해 form의 정보를 불러올 수 있다.
    // req.body.name와 같이 html에서의 name값으로 특정 데이터를 가져올 수도 있다.
      res.send(req.body);
  });
  
  // ... 생략
  ```



### 정적파일

> 이미지, css, js와 같은 정적파일을 저장하고 읽어올 수 있게 설정할 수 있다.

- 최상위 경로에 `uploads`라는 폴더를 만들어주고 아무 이미지 파일을 넣어준다.

- `app.js`에서 static파일의 경로를 지정해준다.

  ```js
  // ... 생략
  
  // 미들웨어 셋팅
  app.use(logger('dev'));
  app.use(bodyParser.json());
  app.use(bodyParser.urlencoded({extended : false}));
  
  // 순서대로 '정적파일을 제공할 url', 정적파일이 있는 폴더 지정
  app.use('/uploads' , express.static('uploads'));
  
  app.get('/', (req, res) => {
      res.send('express start');
  });
  
  // ... 생략
  ```

- 브라우저를 통해 `localhost:3000/uploads/{정적파일명}`으로 이미지가 뜨는지 확인.



### Global View Variable

> 미들웨어를 등록하여 여러 템플릿에서 사용할 수 있는 전역변수를 만드는 방법이다.

- `app.js`에 미들웨어를 등록해준다.

  ```js
  // ... 생략
  
  // 미들웨어 셋팅
  app.use(logger('dev'));
  app.use(bodyParser.json());
  app.use(bodyParser.urlencoded({extended : false}));
  
  app.use('/uploads' , express.static('uploads'));
  
  app.use((req,res,next) => {
      app.locals.isLogin = true;	// isLogin이라는 변수 생성
      next();
  });
  
  // ... 생략
  ```

- 테스트하기 위해 `template/layout/base.html`에 if문을 만들어본다.

  ```html
  <div class="container" style="padding-top:100px;">
  
          {% if isLogin %}
              로그인 중
          {% else %}
              로그인이 안되어있습니다. 
          {% endif %}
  
          {% block content %}{% endblock %}
  
  </div>    
  ```

  - `app.js`에서 `isLogin`을 true로 하면 모든 페이지에 '로그인 중'이 뜰 것이고, false라면 '로그인이 안되어있습니다.'가 뜰 것이다.



### Error Handling (404, 500)

- `template/common` 폴더를 생성하고 각각 `404.html`, `500.html`파일 작성

  - `404.html`

    ```html
    {% set title = "페이자가 없습니다" %}
    {% extends "layout/base.html" %}
    
    {% block content -%}
    
    
    <div class="container">
        <div class="page_area">
            <h1>페이지가 없습니다.</h1>
        </div>
    </div>
    
    {%- endblock %} 
    ```

  - `500.html`

    ```html
    {% set title = "에러가 발생했습니다" %}
    {% extends "layout/base.html" %}
    
    {% block content -%}
    
    
    <div class="container">
        <div class="page_area">
            <h1>에러가 발생했습니다.</h1>
        </div>
    </div>
    
    {%- endblock %} 
    ```

- `app.js`에 미들웨어를 작성해준다. (에러핸들링에 대한 render는 모든 라우팅 다음에 등록해준다.)

  ```js
  // ... 생략
  
  app.use('/admin', vipMiddleware, admin);
  app.use('/contacts', contacts);
  
  // 안쓰이는 매개변수는 '_' 로 대체 가능
  app.use( (req,res, _ ) => {
      res.status(400).render('common/404.html');
  });
  
  app.use( (req,res, _ ) => {
      res.status(500).render('common/500.html');
  });
  
  app.listen(port, () => {
      console.log('Express listening on port', port);
  });
  ```

- 브라우저로 라우팅이 설정되어있지 않은 url로 요청시 '페이지가 없습니다.'가 뜨는지 확인.



### nunjucks의 macro 기능

> 반복되는 html코드를 매크로로 만들어 사용할 수 있는 기능이다.

- 상단바를 만들어 현재 링크에 대한 메뉴가 active되어 찐하게 보이는 기능을 구현할 것이다.

- 현재 링크에 대한 정보를 global variable로 만들어 보겠다.

  - `app.js`에서 `app.locals`로 추가해줄 수 있다.

    ```js 
    // ... 생략
    
    app.use((req,res,next) => {
        app.locals.isLogin = false;
        app.locals.req_path = req.path;	// req.path로 현재 링크에 대한 정보를 가져올 수 있음
        next();
    });
    
    // ... 생략
    ```

- `template/macro/link.html`을 만들어 아래와 같이 작성한다.

  ```html
  {#
      href : 링크
      text : 링크안에 들어갈 텍스트
      current_url : 현재 url
  #}
  
  {% macro link( href, text, current_url) %}
      <li {% if href == current_url %} class="active" {% endif %}>
          <a href="{{ href }}">{{ text }}</a>
      </li>
  {% endmacro %}
  ```

  > `link(href,text,current_url)`이라는 함수를 만드는 것과 같이 생각을 하면 이해가 쉬울 것 같다.

- `base.html`을 다음과 같이 수정한다.

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>{{ title }}</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  </head>
  <body>
      <div class="container" style="padding-top: 10px;">
          <nav class="navbar navbar-inverse"> 
              <div class="container-fluid"> 
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
                      <ul class="nav navbar-nav">
  												
                        	<!-- "macro/link.html"으로부터 link라는 매크로를 불러와 사용한다. -->
                          {% from "macro/link.html" import link %}
                          {{ link( '/admin/products' , "List" , req_path ) }}
                          {{ link( '/admin/products/write' , "Write" , req_path ) }}
  
                      </ul> 
                  </div> 
              </div> 
          </nav>
  
          {% block content %}{% endblock %}
  
      </div> 
  </body>
  </html> 
  ```

  





