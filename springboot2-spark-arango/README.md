# Spring-boot demo with graph database

## Docker-compose

Use docker-compose to startup a database of choice :

```
docker-compose up -d neo4j arango
```


## Database

## ArangoDB setup

```
docker exec -it springboot_graph_arango_1 bash

root@arangodb:/# arangosh
Please specify a password: password


127.0.0.1:8529@_system> db._createDatabase("demo");
true

127.0.0.1:8529@_system> var users = require("@arangodb/users");

127.0.0.1:8529@_system> users.save("dev@demo", "everything");
{
  "user" : "dev@demo",
  "active" : true,
  "extra" : {
  },
  "code" : 201
}

127.0.0.1:8529@_system> users.grantDatabase("dev@demo", "demo");

```


Access to the database using dev@demo :

```
arangosh --server.username "dev@demo" --server.database demo
```

Using web interface (http://localhost:8529)[http://localhost:8529/]
