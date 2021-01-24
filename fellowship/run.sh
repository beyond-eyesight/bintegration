echo "********************************************************"
echo "Waiting for the REDIS server to start  on port $REDIS_PORT"
echo "********************************************************"
while ! `nc -z redis $REDIS_PORT`; do sleep 10; done
echo "******* REDIS has started"

echo "********************************************************"
echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z cassandra $CASSANDRA_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

echo "********************************************************"
echo ">>>>>>>>>>>> Starting fellowship-service "
echo "********************************************************"
java -jar /usr/local/fellowship-service/fellowship-0.0.1.jar
