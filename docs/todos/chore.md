# Chore



## Config

1. encrypt key 수정: ENCRYPT_KEY가 한개밖에 못가짐. 개발, 운영에 따라 다르게 구성할 수 있게 하면 좋을듯. 하지만 우선순위 엄청 뒤.



## Area

1. 모든 컨트롤러에 리퀘스트 매핑 '/areas'



## Cassandra

1. 카산드라 ID 생성을 DB 레벨에서 할 수 있는지 확인

   애플리케이션에서는 ID 안넣어도 DB에서 생성해주는지 확인하기.