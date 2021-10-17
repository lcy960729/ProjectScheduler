# 프로젝트 스케쥴러

## 프로젝트 기획

조별과제와 같이 여러 사람과 함께하는 공동 작업을 관리하는 서비스입니다.

개발자들이 사용하는 협업툴의 형태를 가져와서 조별 과제에 접목시킬 수 있도록 하는것이 목표입니다. 

![main](https://user-images.githubusercontent.com/58020519/137517666-e14186d8-bac6-41ad-83ab-6aeebbc56f9d.png)

## 프로젝트 목표

1. Modular-Monolitic 설계를 통해 마이크로 서비스 및 모놀리틱 서비스를 병행할 수 있도록 구현하자.
2. 서비스 완성 후 DB 캐시 서버 도입 해보기.

## 프로젝트 참여자

- 백엔드 - 이찬영
- 프론트엔드 - 이x혜

## 구현 내용 (마지막 수정 : 2021-10-15)

- **사용자**
    - 회원 가입
    - 로그인
        - 로그인이 완료되면 토큰을 반환한다. 해당 토큰을 포함하여 다른 API 요청시 함께 보낸다.
        
- **프로젝트** (프로젝트 전체를 관리하는 단위)
    - 프로젝트 제목과 프로젝트에 대한 설명의 내용을 추가할 수 있다.
    - 프로젝트 기한을 설정할 수 있다.
    - 프로젝트 내에는 프로젝트 멤버를 추가할 수 있다.
    - 프로젝트 내에는 여러 `세션`들을 생성할 수 있다.

- **세션** (프로젝트 내 분할 되는 작업 단위)
    - 세션 제목과 세션에 대한 설명 회의록 내용을 추가할 수 있다.
    - 세션 기한을 설정할 수 있다.
    - 세션 내에는 여러 세션 멤버를 추가할 수 있다.
    - 세션 내에는 여러 `워크`들을 생성할 수 있다.

- **워크** (세션 내에 분할 되는 작업 단위)
    - 워크 제목과 워크 작업물을 추가할 수 있다.
    - 워크 기한을 설정할 수 있다.
    - 워크 내에는 단 한명의 작업자만 추가할 수 있다.

- **멤버들의 권한**
    - `프로젝트 멤버` (프로젝트에 참여한 사용자)
    - `세션 멤버` (프로젝트 내에 세션에 참가한 사용자)
    - `워커` (세션 내에 워크에 참가한 사용자)
    - 프로젝트, 세션, 워크 참여자들은 각각의 권한을 가진다.
        
        (프로젝트 권한, 세션 권한, 워크 권한 모두 따로 존재한다)
        
    - 각각의 참여자는 각자의 권한을 가진다.
    - 프로젝트에는 `Create`, `Read`, `Update`, `Delete`, `Grant`, `Invite` 권한이 존재한다.
        - `Create`는 하위 작업물을 생성할 수 있는 권한이다.
            
            (프로젝트 권한에서 Create는 Session을 생성할 수 있음을 뜻한다.)
            
        - `Read`는 현재 작업물을 읽을 수 있는 권한이다.
        - `Update`는 현재 작업물을 수정할 수 있는 권한이다.
        - `Delete`는 현재 작업물을 삭제할 수 있는 권한이다.
        - `Grant`는 권한을 변경할 수 있는 권한이다.
        - `Invite`는 프로젝트에 사용자를 추가할 수 있는 권한이다.
    - spring-aop를 통해 설정한 권한과 API에 필요한 권한을 비교하여 API 요청이 가능한지 판단한다.

- **콘텐츠** (프로젝트, 세션, 워크에서 사용자가 작성한 작성물)
    - 작성된 작업물을 일정한 시간마다 실시간으로 저장
        
        (아직은 String만 저장 이후에 사진이나 파일을 올릴 수 있도록 변경할 예정)
        
- **보안**
    - jwt 토큰을 사용한다.
    - spring-aop를 이용하여 컨트롤러에 요청이 들어오기전에 검증을 수행한다.

## 구현 과정

[Modular-Monolitic 설계](https://www.notion.so/Modular-Monolitic-67ccd4780c614b16af87980dde12015e)

[AOP를 이용한 횡단 관심사 구현](https://www.notion.so/AOP-90a102d178784db48d350b6217b4e2e0)

[컨텐츠의 실시간 저장을 위한 메시지 큐 미들웨어 도입](https://www.notion.so/955b817e9f6d4c68a4c4c59f0b020c87)

## 개발 환경

[Backend Server](https://www.notion.so/18b86176f9a64957bb40f071007a81b0)

[Frontend Server](https://www.notion.so/6369e7c2c6344a53b1a597aefb601b4f)