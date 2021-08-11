# java-blackjack
블랙잭 미션을 진행하는 저장소

# first week features
* [X] 참여자의 이름을 입력받고 생성
* [x] 초기 2장 카드 분배
    * [x] 분배할 카드 뽑기
    * [x] 분배된 카드 정보 저장
    * [x] 분배된 카드 공개
* [x] 참여자 플레이
    * [x] 카드 숫자에 따른 판단
    * [x] 카드 추가 분배에 대한 플레이
* [x] 카드 클래스에 값이 아닌 카드 숫자 문자열 추가
* [x] 카드값의 최대를 위한 계산 함수 구현
* [x] 소지한 카드들을 일급객체로 (리팩토링)
* [x] 딜러의 16 규칙에 의한 카드 분배
* [x] 숫자 합산 및 출력
* [x] 결과 판단 및 출력

# first week feedback
* [x] 대소문자 구분 git 설정
* [x] user, dealer 의 상속 구조
* [x] getMoreCardTillUnableOrDeny 메소드 Game의 책임으로
* [x] 부족한 Testcode 추가
* [x] 카드 뽑기 로직 수정 (처음 섞고, 한 장씩 뽑도록)
* [x] 카드의 값 모양(delimiter) enum 으로 관리
* [x] returnOneIfDrawerElseReturnZero() in UserInfoDto, boolean 반환으로
  * [x] returOne~ElseReturnZero 메소드들 반환값 boolean
* [x] Dto (UserInfoDto) 에서 로직 제거
* [x] last more feedback

# second week features
* [x] 이름 입력 직후, user 베팅 금액 입력받기
* [x] revealInitialCards 직후, player 블랙잭 판정 로직 구현하기
* [x] 결과 판정 단계, 버스트 판정 로직 구현하기
* [x] 승패 판정 -> 배팅 판정 로직 변경 (블랙잭, 버스트 고려)
* [x] 수익 계산 로직 구현하기
* [x] 배팅 결과 출력하기
* [x] 테스트 코드 빠진 부분 채우기

# second week feedback
* [x] User 와 UserInfoDto 의 의존관계 정리
* [x] Player.java의 getIsBlackjack 메소드 네이밍 변경
* [ ] Game.java의 judegePlayerBurst 메소드 리팩토링
* [ ] CardPack.java의 리펙토링