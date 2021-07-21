# java-blackjack
블랙잭 미션을 진행하는 저장소

# features
* [*] 참여자의 이름을 입력받고 생성
* [*] 초기 2장 카드 분배
    * [*] 분배할 카드 뽑기
    * [*] 분배된 카드 정보 저장
    * [*] 분배된 카드 공개
* [*] 참여자 플레이
    * [*] 카드 숫자에 따른 판단
    * [*] 카드 추가 분배에 대한 플레이
* [*] 카드 클래스에 값이 아닌 카드 숫자 문자열 추가
* [*] 카드값의 최대를 위한 계산 함수 구현
* [*] 소지한 카드들을 일급객체로 (리팩토링)
* [*] 딜러의 16 규칙에 의한 카드 분배
* [*] 숫자 합산 및 출력
* [*] 결과 판단 및 출력

# feedback
* [*] 대소문자 구분 git 설정
* [*] user, dealer 의 상속 구조
* [*] getMoreCardTillUnableOrDeny 메소드 Game의 책임으로
* [*] 부족한 Testcode 추가
* [*] 카드 뽑기 로직 수정 (처음 섞고, 한 장씩 뽑도록)
* [*] 카드의 값 모양(delimiter) enum 으로 관리
* [ ] returnOneIfDrawerElseReturnZero() in UserInfoDto, boolean 반환으로
  * [ ] returOne~ElseReturnZero 메소드들 반환값 boolean
* [ ] Dto (UserInfoDto) 에서 로직 제거
