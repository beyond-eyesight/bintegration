# Refac

## ECS

1. 현재 ecs에 host로 배포해놓음. awsvpc로 변경해야함

## Discovery

1. eureka -> k8s



## Access

1. jpa 연관관계 주의할점 살피고 고치기

## Spring

1. 현재 컨피그 파일을 아예 나눔. 로컬과 로컬이 아닌걸로. 근데 아예 하나의 클래스에서 로컬인지 아닌지에 따라 다르게 할 수도 있을듯
2. 모든 서비스들의 active profile을 한번에 바꿔줄 수 있게 수정. 지금은 로컬로 바꾸려면 가각ㄱ 가서 다 로콜러 바꿔저야;....
3. controller validation  - 컨트롤러 레벨에서 파라미터 검증



## Deploy

1. 80 -> 5555 포워딩



## Docker

1. 개발 단계를 위해 compose 배포 - ecs에 docker-compose 배포 후 docker-compose up - 현재는 이미지가 배포후 시작되고 있는데, 그러다보니 다른 인프라 요소들이 없어서 열리질 않는다. 

2. dockerfile에 jar 파일 이름 하드코딩돼있는거 삭제

3. Dockerfile의 위치를 내 마음대로 제어하기 - 현재 chat 앱을 도커라이징하는 도커파일은 루트에 있다.

   ./gradle docker를 활용하여 원하는대로 제어한 줄 알앗는데, 자동화하는 사이 아직 아니란걸 알앗다 수정.

4. docker 디렉토리 위치 변경 - 도커 디렉토리 resources로 옮기기