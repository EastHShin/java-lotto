# java-lotto

로또 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 구현 사항
### 

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.

  ### 로또를 구입한다.
    - 로또 1장의 가격은 1000원이다.
    - 최소 입력 금액 1000원
    - 최대 100,000원 제한
    - 숫자만 입력 가능
    - 에러가 발생했을 경우에는 다시 입력 받도록 구현
    - 에러 횟수가 일정 횟수 이상 넘어갈 경우 프로그램 종료

  ### 로또를 발급한다.
    - 구입 금액에 1000으로 나누어 떨어지는 수만큼 로또 발급(n개를 구매했습니다 출력)
        - 각 로또를 만들기 위해 6개의 난수 생성(난수는 고유해야 한다)
        - 로또 번호는 정렬되어 있어야 한다.
        - 하나의 로또에 포함된 난수는 ‘[’으로 시작해 각 난수를 ‘, ‘로 구분하고 ‘]’로 끝나야 한다.(출력)
        - 한 줄에 하나의 로또 만 출력해야 한다(출력)
    
  ### 게임의 결과를 반환한다.
    - 당첨 번호를 입력
        - 입력은 “1, 2, 3, 4, 5, 6” 형식으로 이루어진다.
        - 숫자가 6개가 아닐 경우 에러를 발생시킨다.
        - 숫자가 아닐 경우 에러를 발생시킨다.
        - 숫자가 1~45 범위가 아닐 경우 에러를 발생시킨다.
    - 보너스 번호 입력
        - 숫자를 한 개만 입력한 것이 아닐 경우 에러를 발생시킨다.
        - 숫자가 아닐 경우 에러를 발생시킨다.
        - 숫자가 1~45 범위가 아닐 경우 에러를 발생시킨다.
    - 당첨 번호 + 보너스 번호의 중복 확인
        - 당첨 번호와 보너스 번호를 확인하여 중복이 존재할 경우 다시 입력받는다.
    - 당첨 통계
        - 자동 추첨된 로또 번호들을 순회하면서 입력된 당첨번호와 보너스 볼과 비교한 뒤 n등 당첨을 산출
        - 수익률은 당첨 금액 / 구입 금액(소수점 2자리 수)
    - 결과 출력
        - 3개 ~ 6개 까지 일치하는 로또 개수를 출력한다.
        - 총 수익률을 소수점 2자리 까지 출력한다.
    
---
## 2단계 구현 목록
- [x] LotteryGame의 게임 결과 도출 행위를 새로운 클래스를 만들어 책임 이전
- [ ] 로또 번호를 수동으로 생성할 수 있도록 기능 추가
- [ ] 수동으로 구매할 로또 수를 입력받는다.
    - [x] 로또 구매 개수보다 많으면 에러를 발생시킨다.