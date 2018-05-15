# Spring-boot demo with graph database


## Docker-compose

Use docker-compose to startup a database of choice :

```
docker-compose up -d neo4j
```



## Database

### Neo4j setup


```
curl -v -u neo4j:neo4j -X POST localhost:7474/user/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}

```

Note : Neo4j requires to change password at first login

