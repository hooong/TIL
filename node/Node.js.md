# Node.js

### Node.js란?

> - 웹브라우저에서 쓰이는 자바스크립트를 서버에서 사용가능하게 하는 프레임워크
> - 크롬에 탑재된 자바스크립트 엔진인 V8이 탑재되어있다.
> - 대용량 서비스를 위해 Ryan Lienhart Dahl이 개발하였다.



### Node.js 설치하기

- [https://nodejs.org/en/download/](https://nodejs.org/en/download/)에서 자신의 운영체제에 맞게 다운로드

  - LTS(Long Term Suport)로 다운로드

- 설치 확인하기

  - 터미널 실행 후 아래 명령어로 버전 확인으로 설치가 제대로 되었는지 확인. 

    `$ node -v`  => `v12.18.1`



### 모듈 패턴

- 내보낼 때

  - `module.exports.변수(또는 함수)`

- 불러올 때

  - `require 파일명`
  - 내부모듈의 경우 require가 필요 없이 사용 가능

- Ex)

  - `myvar.js`

    ```js
    moduel.exports.a = "hello a";
    ```

  - `index.js`

    ```js
    const myvar = require('./myvar');
    
    console.log(myvar.a);
    ```

  - `index.js` 파일 실행

    ```
    // output
    
    hello a
    ```

- node 파일 실행하기
  - `$ node js파일명`



### npm이란?

> node package manager로 다른 사람들이 모듈을 패키지로 만들어 올려 놓은 것을 설치하고 관리할 수 있는 것.

- node 프로젝트 초기화

  - `$ npm init`

    ```
    package name: (test) 
    version: (1.0.0) 
    description: 
    entry point: (index.js) 
    test command: 
    git repository: 
    keywords: 
    author: 
    license: (ISC) 
    ```

    - 프로젝트의 정보를 입력 (그냥 enter를 치면 빈값이나 default값으로 채워짐.)

      - `$ npm init -y`로 실행 시 위의 입력 과정 생략 가능

    - 완료 시 `package.json` 파일 생성

      ```
      {
        "name": "test",
        "version": "1.0.0",
        "description": "",
        "main": "index.js",
        "scripts": {
          "test": "echo \"Error: no test specified\" && exit 1"
        },
        "author": "",
        "license": "ISC"
      }
      ```

      > 프로젝트에 대한 정보를 담는 파일

    - `package-lock.json` : 각 모듈의 하위 참조되는 모듈의 버전에 대한 정보를 담는 파일

      - 각 모듈간의 충돌을 막을 수 있게 도와준다.

- 모듈 설치

  - `$ npm install 모듈명`

    - Ex) `$ npm install express` 

      - 성공적으로 설치 완료가 되면 `node_modules`라는 폴더가 생성되고 모듈이 설치되어 있음.

      - `index.js`에서 `express`모듈 사용하기

        ```js
        const express = require('express');
        ```

        - Package.json때문에 require시 경로를 적지않고도 사용가능한 것.

- Package scripts 설정

  - `package.json`

    ```
    {
      ... 생략
      
      "scripts": {
        "start" : "node index.js",
        "dev" : "node index.js"
      },
     	
     	... 생략
    }
    ```

    > - 해당 패키지의 경로에서 scripts에 특정 명령어에 이름?을 붙일 수 있다.
    > - `start`는 npm의 예약어로 `$ npm start`로 실행가능
    > - `dev`는 npm의 예약어가 아니므로 `$ npm run dev`로 scripts를 실행할 수 있다.



### nodemon

> 파일의 변경이 감지될 때 자동으로 재시작을 해주는 툴

- 설치하기
  - `$ sudo npm istall -g nodemon`
    - `-g` : global로 설치 => 모듈을 해당 프로젝트 폴더가 아닌 시스템 영역에 설치
- 실행하기
  - `$ nodemon index.js`
- nodemon이 설치 되어있지 않은 환경에서 발생할 수 있는 문제 예방하기
  - `package.json`의 scripts 지정시 `npx` 사용 => 해당 모듈이 없다면 설치 후 실행
  - `"start" : "npx nodemon index.js"`