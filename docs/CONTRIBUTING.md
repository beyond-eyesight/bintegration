# Contribution Guide

## 1. 절차

1. [https://github.com/beyond-eyesight/bintegration](https://github.com/beyond-eyesight/bintegration)을 `fork` 하기
2. `fork` 한 리포지토리 `clone`
3. 작업 할 내용을 이슈에 등록하기 or 이슈에 이미 등록된 작업 선택하기(표시 필요)
5. `develop` 브랜치 `rebase`
6. 브랜치 이름 규칙에 따라 작업할 브랜치 생성
7. 작업 수행(원칙적으로 단위 테스트 작성, 예외적으로 설명 후 단위 테스트 미작성 허용)
8. 모든 테스트 통과시 작업한 이슈를 레퍼런스하여 `develop` 브랜치에 Pull Request
10. `master` 는 모든 테스트 케이스를 통과하며 빌드 에러가 없고 milestone 지점에 `rebase and merge`

## 2. 이슈

이슈는 두 가지 카테고리(제목, 태그)로 관리합니다.

1. 제목: 작업 분류

   \- FEAT

   \- TEST

   \- REFAC

   \- DOCS

   \- BUG

   \- CONSIDER

   \- QUESTION

   \- CHORE

   \- DEPRECATE

2. 태그: 관련 서비스 이름

## 3. 브랜치 

- `master` : 릴리즈 수준의 코드

- `develop` : 테스트 완료된 개발 코드

- custom branch - 

  1. 형식

     > [type]/[description]예시

  2. 예시

     > feature/#20 
     >
     > refactor/log-in
     >
     > ...

## 4. 커밋 메시지

3. 형식

   > \[type\]: [description]

4. 예시

   > docs: User 관련 시나리오 작성 [ISSUE-1]
   >
   > feat: UserService의 add() [ISSUE-2]

5. type 종류

   > \- feat : 새로운 기능
   >
   > \- fix : 버그 대처
   >
   > \- refactor : 코드 수정
   >
   > \- test : 테스트 추가
   >
   > \- docs : 문서 작성
   >
   > \- perf : 성능 관련 코드 작성
   >
   > \- critical : 아키텍처에 영향을 미칠만한 영향력이 큰 코드 수정
   >
   > \- chore : 기타 등등의 허드렛일

## 5. Tip

- 최대한 다른 사람과 작업하는 부분이 겹치지 않도록 feature를 작게 잡고 작업해주세요.
- 이슈는 다른 사람들이 볼 때 쉽게 이해할 수 있도록 되도록이면 구체적으로 작성해주세요.