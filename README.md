# java-lotto-precourse

# 로또

## 프로젝트 소개

- 사용자가 입력한 구입 금액에 따라 로또를 발행하고, 사용자가 입력한 당첨 번호 및 보너스 번호와 비교하여 당첨 통계 및 수익률을 계산합니다.
- MVC 패턴을 기반으로 도메인, 서비스, 컨트롤러 계층을 분리하여 설계를 진행하였습니다.

## 주요 기능 목록

### 핵심 기능

- [ ]  로또 구입 금액 입력 받기
- [ ]  구입 금액 1,000원 당 1장의 로또 발행
- [ ]  로또 번호 생성
    - [ ]  각 로또는 1~45 사이의 중복되지 않는 6개 숫자로 자동 생성
    - [ ]  발행한 로또의 개수와 번호를 오름차순 정렬하여 전체 출력
- [ ]  당첨 번호 6개(쉼표로 구분) 입력
- [ ]  보너스 번호 1개 입력
- [ ]  구매한 모든 로또를 당첨 번호, 보너스 번호와 비교하여 등수(1~5등, 당첨 x) 판별
- [ ]  당첨 내역 출력(번호 일치 개수, 받는 금액, 당첨 개수)
- [ ]  총 수익률을 계산하여 소수점 둘째 자리에서 반올림하여 출력

### 예외 처리

`IllegalArgumentException`을 발생시키고, `[ERROR]`로 시작하는 메시지 출력 후 해당 부분을 다시 입력

- 구입 금액 입력 관련 예외 처리
    - [ ]  숫자가 아닌 값(문자, 공백 등) 입력 시 예외 처리
    - [ ]  1,000원 단위가 아닐 시 예외 처리
    - [ ]  0 또는 음수 입력 시 예외 처리
- 당첨 번호 입력 관련 예외 처리
    - [ ]  쉼표(,)로 구분된 숫자가 6개가 아닐 시 예외 처리
    - [ ]  숫자가 아닌 값 포함 시 예외 처리
    - [ ]  1~45 범위를 벗어난 숫자 포함 시 예외 처리
    - [ ]  중복된 숫자 포함 시 예외 처리
    - [ ]  쉼표 앞/뒤가 비어있는 경우 예외 처리
- 보너스 번호 입력 관련 예외 처리
    - [ ]  숫자가 아닌 값 입력 시 예외 처리
    - [ ]  1~45 범위를 벗어난 숫자 입력 시 예외 처리
    - [ ]  이미 입력된 당첨 번호들과 중복 시 예외 처리

```
src
└── main
    └── java
        └── lotto
            ├── Application.java
            ├── controller
            │   └── LottoController.java
            ├── domain
            │   ├── Lotto.java
            │   ├── LottoNumberGenerator.java
            │   ├── CheckLottoWinningRank.java
            │   └── WinningNumbers.java
            ├── service
            │   ├── LottoPurchaseService.java
            │   ├── LottoStatisticsService.java
            │   └── LottoRateOfReturnService.java
            ├── util
            │   └── InputValidator.java
            └── view
                ├── InputView.java
                └── OutputView.java

```