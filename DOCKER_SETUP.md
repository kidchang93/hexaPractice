# 🐳 Docker MySQL 설정 가이드

## 📋 사전 요구사항

- Docker 설치 확인
```bash
docker --version
docker-compose --version
```

## 🚀 빠른 시작

### 1. MySQL 컨테이너 실행

```bash
# 백그라운드에서 실행
docker-compose up -d

# 로그 확인
docker-compose logs -f mysql
```

### 2. 컨테이너 상태 확인

```bash
# 실행 중인 컨테이너 확인
docker-compose ps

# MySQL이 준비될 때까지 대기 (헬스체크)
docker-compose ps mysql
```

### 3. Spring Boot 애플리케이션 실행

```bash
./gradlew bootRun
```

---

## 🎯 주요 명령어

### Docker Compose 명령어

```bash
# 컨테이너 시작
docker-compose up -d

# 컨테이너 중지
docker-compose stop

# 컨테이너 중지 및 제거
docker-compose down

# 컨테이너 + 볼륨 제거 (데이터 삭제 주의!)
docker-compose down -v

# 컨테이너 재시작
docker-compose restart

# 로그 실시간 확인
docker-compose logs -f mysql

# 컨테이너 상태 확인
docker-compose ps
```

### MySQL 컨테이너 접속

```bash
# 방법 1: docker exec
docker exec -it hexapractice-mysql mysql -uroot -proot1234

# 방법 2: docker-compose exec
docker-compose exec mysql mysql -uroot -proot1234

# 특정 데이터베이스로 접속
docker-compose exec mysql mysql -uroot -proot1234 hexaPractice
```

---

## 🔧 설정 정보

### MySQL 연결 정보

```yaml
Host: localhost
Port: 3306
Database: hexaPractice

# Root 계정
Username: root
Password: root1234

# 일반 사용자 계정 (선택사항)
Username: hexa_user
Password: hexa1234
```

### DBeaver 연결 설정

1. **New Connection** → **MySQL**
2. 연결 정보 입력:
   - Server Host: `localhost`
   - Port: `3306`
   - Database: `hexaPractice`
   - Username: `root`
   - Password: `root1234`
3. **Test Connection** → **Finish**

---

## 📂 디렉토리 구조

```
hexaPractice/
├── docker-compose.yml           # Docker Compose 설정
├── docker/
│   └── mysql/
│       └── init/
│           └── 01-init.sql     # 초기화 SQL (선택사항)
└── src/
    └── main/
        └── resources/
            └── application.yml  # Spring Boot 설정
```

---

## 🔍 데이터 영속성

Docker 볼륨을 사용하여 데이터를 영구 저장합니다:

```bash
# 볼륨 목록 확인
docker volume ls

# hexaPractice 볼륨 확인
docker volume ls | grep hexapractice

# 볼륨 상세 정보
docker volume inspect hexapractice_mysql-data
```

**⚠️ 주의**: `docker-compose down -v`를 실행하면 데이터가 삭제됩니다!

---

## 🛠️ 문제 해결

### 포트 3306이 이미 사용 중인 경우

```bash
# 포트 사용 확인 (macOS/Linux)
lsof -i :3306

# 포트 사용 확인 (Windows)
netstat -ano | findstr :3306

# 해결 방법 1: 기존 MySQL 중지
brew services stop mysql
# 또는
sudo systemctl stop mysql

# 해결 방법 2: docker-compose.yml에서 포트 변경
ports:
  - "3307:3306"  # 호스트 포트 변경
```

### 컨테이너가 시작되지 않는 경우

```bash
# 로그 확인
docker-compose logs mysql

# 컨테이너 제거 후 재시작
docker-compose down
docker-compose up -d
```

### 데이터베이스 초기화

```bash
# 볼륨 포함 전체 삭제
docker-compose down -v

# 다시 시작 (새로운 데이터베이스)
docker-compose up -d
```

### 권한 문제

```bash
# MySQL 컨테이너 접속
docker-compose exec mysql bash

# MySQL 접속
mysql -uroot -proot1234

# 권한 확인 및 부여
SELECT user, host FROM mysql.user;
GRANT ALL PRIVILEGES ON hexaPractice.* TO 'root'@'%';
FLUSH PRIVILEGES;
EXIT;
```

---

## 📊 유용한 SQL 명령어

컨테이너에 접속한 후:

```sql
-- 데이터베이스 확인
SHOW DATABASES;

-- hexaPractice 사용
USE hexaPractice;

-- 테이블 확인
SHOW TABLES;

-- products 테이블 구조
DESCRIBE products;

-- 데이터 조회
SELECT * FROM products;

-- 데이터베이스 크기 확인
SELECT 
    table_schema AS 'Database',
    ROUND(SUM(data_length + index_length) / 1024 / 1024, 2) AS 'Size (MB)'
FROM information_schema.tables
WHERE table_schema = 'hexaPractice'
GROUP BY table_schema;
```

---

## 🔐 보안 권장사항

### 개발 환경

현재 설정은 **개발 환경용**입니다. 간단한 비밀번호 사용.

### 운영 환경

운영 환경에서는 환경 변수 파일(`.env`) 사용을 권장합니다:

#### `.env` 파일 생성

```bash
# .env
MYSQL_ROOT_PASSWORD=강력한비밀번호123!
MYSQL_DATABASE=hexaPractice
MYSQL_USER=hexa_user
MYSQL_PASSWORD=사용자비밀번호456!
```

#### `docker-compose.yml` 수정

```yaml
services:
  mysql:
    environment:
      MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
      MYSQL_DATABASE: ${MYSQL_DATABASE}
      MYSQL_USER: ${MYSQL_USER}
      MYSQL_PASSWORD: ${MYSQL_PASSWORD}
```

**⚠️ 중요**: `.env` 파일은 `.gitignore`에 추가하여 Git에 커밋하지 마세요!

---

## 📝 추가 팁

### 1. MySQL 성능 최적화

`docker-compose.yml`에 추가:

```yaml
command:
  - --character-set-server=utf8mb4
  - --collation-server=utf8mb4_unicode_ci
  - --max-connections=200
  - --innodb-buffer-pool-size=256M
```

### 2. 백업

```bash
# 데이터베이스 백업
docker-compose exec mysql mysqldump -uroot -proot1234 hexaPractice > backup.sql

# 복원
docker-compose exec -T mysql mysql -uroot -proot1234 hexaPractice < backup.sql
```

### 3. 여러 환경 설정

```bash
# 개발 환경
docker-compose -f docker-compose.yml up -d

# 테스트 환경
docker-compose -f docker-compose.test.yml up -d
```

---

## ✅ 체크리스트

실행 전 확인사항:

- [ ] Docker 설치 확인
- [ ] Docker 서비스 실행 중
- [ ] 포트 3306 사용 가능 확인
- [ ] `docker-compose.yml` 파일 존재
- [ ] `application.yml`의 비밀번호가 `docker-compose.yml`과 일치

실행 순서:

1. `docker-compose up -d`
2. `docker-compose ps` (상태 확인)
3. `./gradlew bootRun`
4. API 테스트 또는 DBeaver 연결

---

## 📞 문제가 있나요?

1. 로그 확인: `docker-compose logs -f mysql`
2. 컨테이너 재시작: `docker-compose restart`
3. 완전 재시작: `docker-compose down && docker-compose up -d`

