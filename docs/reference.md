# JBOSS

1. cli

   add module

   ./jboss-cli.sh --command="module add --name=beyondeyesight.user.infra.adapter.keycloak --resources=../standalone/deployments/access-0.0.1.jar --dependencies=org.keycloak.keycloak-core,org.keycloak.keycloak-server-spi"



# SpringBoot

1. [spring boot 2.4 config 파일](https://spring.io/blog/2020/08/14/config-file-processing-in-spring-boot-2-4)

# Script

1. run.sh에서 포트 기다리기

   ```shell
   echo "Waiting for the REDIS server to start on port $REDIS_PORT"
   while ! nc -z redis $REDIS_PORT; do sleep 10; done
   echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
   while ! nc -z cassandra $CASSANDRA_PORT; do sleep 3; done
   ```

   

# IDEA

1. 인간관계는 복합적이고 친밀도는 하나다. 그리고 평가는 다양할 수 있다.

   친구이기도 하고, 보스이기도하고 직장동료일수도 있는 것.
   반면에 친한 정도는 하나. 친하면 친한거고 안친하면 안친한거.
   그리고 평가는 다양할수있음 친하더라도 평가는 나쁠수도있는것. 이거는 관계에 따라 달라질 수 있음. 보스로서는 어떻고, 친구로서는 어떻고.



# Cassandra

1. [overview with slide share](https://www.slideshare.net/AnirvanChakraborty1/running-cassandra-on-amazons-ecs)



# ElasticSearch

1. [elasticsearch aws 연동](https://medium.com/@prasanth_rajendran/how-to-integrate-spring-boot-elasticsearch-data-with-aws-445e6fc72998)

# ECS

## 1. cli

**Detail**
ecs-cli up --keypair beyondeyesight --instance-role ecsInstanceRole --size 2 --instance-type t2.micro --cluster-config cluster-root-config --ecs-profile root-profile


ecs-cli up --keypair beyondeyesight --instance-role ecsInstanceRole --size 1 --instance-type t2.large --cluster test-cluster1 --region ap-northeast-2 --ecs-profile root-profile --vpc vpc-037c26b2aa1b15c22 --subnets subnet-009f02eea9b4cb38b,subnet-095bf44a86d0f59ac --launch-type EC2 


ecs-cli compose --region ap-northeast-2 --cluster test-cluster1 --ecs-profile root-profile --file scripts/deploy/dev/docker-compose.yml up

ecs-cli compose --region ap-northeast-2 --cluster test-cluster1 --ecs-profile root-profile --file scripts/deploy/dev/docker-compose.yml --ecs-params scripts/deploy/dev/ecs-params.yml up


ecs-cli down --force --cluster test-cluster3

ecs-cli compose --file scripts/deploy/dev/docker-compose.yml --project-name fellowship --ecs-params scripts/deploy/dev/ecs-params.yml --region ap-northeast-2 --cluster test-cluster1 --ecs-profile root-profile service up


ecs-cli scale --capability-iam  --size 2 --cluster test-cluster1 --region ap-northeast-2 --ecs-profile root-profile 



# CQL

**Detail**

**data initialization**

INSERT INTO chat_room ("name", "room_id") VALUES ('Wangsimni', 110841e3-e6fb-4191-8fd8-5674a5107c33);
INSERT INTO chat_room ("name", "room_id") VALUES ('Campus', 4f0a4a02-26c6-4441-915d-c0f61cda0178);
INSERT INTO chat_room ("name", "room_id") VALUES ('Dormitory', cd865f7d-3923-4065-a5e6-5c093e7b5442);
INSERT INTO chat_room ("name", "room_id") VALUES ('Itaewon', 56645394-80e0-4fb3-a2ad-097832e39233);

**schema**

CREATE TYPE IF NOT EXISTS sender (id uuid);

CREATE TABLE IF NOT EXISTS chatmessage (room_id uuid, msg_id uuid, body text, sender frozen, PRIMARY KEY (room_id, msg_id)) WITH CLUSTERING ORDER BY (msg_id ASC);

CREATE TABLE IF NOT EXISTS chat_room (name text, room_id uuid, PRIMARY KEY (name, room_id)) WITH CLUSTERING ORDER BY (room_id ASC);

aws

CREATE TABLE "testKeySpace"."chat_room"(
"room_id" uuid,
"name" text,
PRIMARY KEY("room_id"))
WITH CUSTOM_PROPERTIES = {
'capacity_mode':{'throughput_mode':'PAY_PER_REQUEST'}
} AND TAGS = {'Name' : 'Test ChatRoom'}



# Spring Cloud Gateway

1. [token relay 관련](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#token-relay-gatewayfilter-factory)
2. 

