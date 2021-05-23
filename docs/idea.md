# Idea

## 1. Architecture

1. 추가 서비스 : 경매 통계 협업

## 2. Access

1. User를 어디까지 의존하게 할 것인가

   포조?
   spring data까지?
   Keycloak까지?

   1) 포조까지
   일단 jpa에서 user 인터페이스를 잘 만들어주는지, 맵핑을 잘해주는지부터 확인해야함.
   그게 되면 문제는 없는데,
   근디 이렇게하면 코드 대부분은 인프라에 있게될 것임.
   인프라와 겹치지 않는 User의 도메인 로직이 거의 없을거같다.

   2) 스프링 데이터까지?
   보통 이렇게는 많이 하는걸로 알고있는데,
   이렇게하면 아예 keycloak까지 하지 굳이 spring data로 할 이유는 없을것같음.
   spring data를 허용하는게 어차피 spring data는 거의 안바뀔 거대한 것이라고 생각하기 때문인데,
   keycloak도 마찬가지

   3) keycloak까지 해버리면 레이어를 4개로 나눌 이유가 없고, 그냥 기술에 의존하게 하는 것.

   ref: https://github.com/Baeldung/spring-security-oauth/blob/master/oauth-rest/keycloak-custom-providers/src/main/java/com/baeldung/auth/provider/user/CustomUser.java

2. Role

   tester, observer

3. 도메인 모델 - realm

4. Username을 뭘로 볼지 정하기

   id를 username이라고 할것인가? 걍 내이름을 이름이라고 할것인가?

5. User에 Phone Number 필드 추가

## 3. Zone

1. Zone에 count 넣어줘야 함

2.  zone 엔티티에 active 플래그 넣는거 괜찮을지

3. initial data를 onContextRefreshed 대신 설정파일에서 zone 정보를 가지는게 나을듯

4. Zone도 추상화하고, HYZone을 enum으로 만들어서 관리하는게 나을듯

5. Zone 안에 있는 사람 명명

   Member로 하거나, withinZone으로 하거나,,,

   후보
   Member

   Tenant : 얘네는 그냥 보안을 마친 사용자로 쓰는게 나을수도
   Occupant
   Villager
   Enterer

   Admittee

6. 