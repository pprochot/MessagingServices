docker-compose up -d rabbitmq-1
docker-compose up -d rabbitmq-2
docker-compose up -d rabbitmq-3

sleep 20

docker exec -it rabbit-1 rabbitmq-plugins enable rabbitmq_federation
docker exec -it rabbit-2 rabbitmq-plugins enable rabbitmq_federation
docker exec -it rabbit-3 rabbitmq-plugins enable rabbitmq_federation

docker exec -it rabbit-1 rabbitmqctl set_policy ha-fed ".*" '{"federation-upstream-set":"all", "ha-mode":"nodes", "ha-params":["rabbit@rabbit-1", "rabbit@rabbit-2", "rabbit@rabbit-3"]}' --priority 1 --apply-to queues