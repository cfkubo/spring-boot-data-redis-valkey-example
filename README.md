Spring Boot Data Redis - example with Redis and Valkey
=

The purpose of this repository is to check that Valkey works fine with Spring Data Redis as drop-in replacement.

Following versions of Redis and Valkey are covered in this sample project:

| Storage | Version | Docker image                     |
|---------|---------|----------------------------------|
| Redis   | 7.2     | `redis:7.2.7-alpine3.21`         |
| Redis   | 7.4     | `redis:7.4.2-alpine3.21`         |
| Valkey  | 7.2     | `valkey/valkey:7.2.8-alpine3.21` |
| Valkey  | 8.0     | `valkey/valkey:8.0.2-alpine3.21` |

## Docker containers

### Redis

#### 7.4

```shell
docker run -d --name redis72a \
  --network=localnet \
  -p 6379:6379 \
  redis:7.2.7-alpine3.21
```

#### 7.4

```shell
docker run -d --name redis74a \
  --network=localnet \
  -p 6379:6379 \
  redis:7.4.2-alpine3.21
```

### Valkey

#### 7.2

```shell
docker run -d --name valkey72a \
  --network=localnet \
  -p 6379:6379 \
  valkey/valkey:7.2.8-alpine3.21
```

#### 8.0

```shell
docker run -d --name valkey80a \
  --network=localnet \
  -p 6379:6379 \
  valkey/valkey:8.0.2-alpine3.21
```