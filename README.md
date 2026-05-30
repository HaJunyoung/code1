# 🍗 MSA 기반 B2B 물류 운영 플랫폼, "*BoxOffice*"
- 📌 전국 17개 허브를 기반으로 B2B 물류의 주문·배송·업체·상품 관리를 처리하는 MSA 기반 물류 운영 플랫폼입니다. 주요 도메인을 독립 서비스로 분리하고, 주문 및 배송 상태 변경을 Kafka 이벤트로 발행하여 Slack 알림과 AI 기반 발송 시한 산출 기능이 비동기적으로 연동되도록 설계했습니다.

## 👥 팀원 역할분담
| **이름** | **GitHub** | **역할 (Domain)** | **주요 업무 및 성과** |
| --- | --- | --- | --- |
| **손형호 (리더)** | <a href="https://github.com/GolemOnce"><img src="https://img.shields.io/badge/GitHub-GolemOnce-181717?style=flat-square&logo=github&logoColor=white"/></a> | Delivery Domain | 프로젝트 총괄 및 일정 관리, 배송 도메인 API 구현 |
| **권효승** | <a href="https://github.com/hy-ogu"><img src="https://img.shields.io/badge/GitHub-hy--ogu-181717?style=flat-square&logo=github&logoColor=white"/></a> | Notification / Slack Domain, AI Domain | 알림 및 Slack 연동 기능 구현, AI 도메인 API 구현 |
| **박주원** | <a href="https://github.com/k-r-1"><img src="https://img.shields.io/badge/GitHub-k--r--1-181717?style=flat-square&logo=github&logoColor=white"/></a> | Company & Product Domain | 업체 및 상품 도메인 API 구현 |
| **오영현** | <a href="https://github.com/dddd2356"><img src="https://img.shields.io/badge/GitHub-dddd2356-181717?style=flat-square&logo=github&logoColor=white"/></a> | Hub Domain | 공통 모듈 및 허브 도메인 API 구현 |
| **하준영** | <a href="https://github.com/HaJunyoung"><img src="https://img.shields.io/badge/GitHub-HaJunyoung-181717?style=flat-square&logo=github&logoColor=white"/></a> | User & Auth Domain, Delivery Manager | 사용자 및 인증 기능 구현, 배송 담당자 도메인 API 구현, 인프라 설계 |
| **한혜수** | <a href="https://github.com/hyesuhan"><img src="https://img.shields.io/badge/GitHub-hyesuhan-181717?style=flat-square&logo=github&logoColor=white"/></a> | Order Domain | 주문 도메인 API 구현 |

<br>


## 🔍 프로젝트 목적/상세

### 🛠️ Tech Stack

| 분야 | 기술 |
|------|------|
| 💻 Backend | ![Java](https://img.shields.io/badge/Java_21-ED8B00?style=flat-square&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.5.14-6DB33F?style=flat-square&logo=springboot&logoColor=white) ![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=spring&logoColor=white) ![QueryDSL](https://img.shields.io/badge/QueryDSL_5.1.0-0769AD?style=flat-square)|
| 🔨 Build | ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white) |
| ☁️ MSA | ![Spring Cloud](https://img.shields.io/badge/Spring_Cloud_2025.0.2-6DB33F?style=flat-square&logo=spring&logoColor=white) ![Gateway](https://img.shields.io/badge/Gateway-6DB33F?style=flat-square&logo=spring&logoColor=white) ![Eureka](https://img.shields.io/badge/Eureka-6DB33F?style=flat-square&logo=spring&logoColor=white) ![OpenFeign](https://img.shields.io/badge/OpenFeign-6DB33F?style=flat-square&logo=spring&logoColor=white) |
| 🗄️ Database | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL_15-4169E1?style=flat-square&logo=postgresql&logoColor=white) |
| ⚡ Cache | ![Redis](https://img.shields.io/badge/Redis_7-DC382D?style=flat-square&logo=redis&logoColor=white) |
| 📡 Messaging | ![Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=flat-square&logo=apachekafka&logoColor=white) |
| 🔐 Security | ![Keycloak](https://img.shields.io/badge/Keycloak_26.0.0-4D4D4D?style=flat-square&logo=keycloak&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square&logo=jsonwebtokens&logoColor=white) |
| 🤖 AI | ![Gemini API](https://img.shields.io/badge/Gemini_API-4285F4?style=flat-square&logo=google-gemini&logoColor=white) |
| 🚀 Infra | ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white) ![Docker Compose](https://img.shields.io/badge/Docker_Compose-2496ED?style=flat-square&logo=docker&logoColor=white) |
| 📊 Monitoring | ![Zipkin](https://img.shields.io/badge/Zipkin-FE7A16?style=flat-square) |

<br>

### ⚙️ System Architecture

#### 🧩 Conceptual Architecture
API Gateway를 단일 진입점으로 하여 라우팅 및 전역 인증(JWT)을 처리하며, 내부 서비스 간에는 동기적 FeignClient 호출과 비동기적 Kafka 이벤트 연동을 전략적으로 혼합하여 사용합니다.
<p align="center">
  <img src="https://github.com/user-attachments/assets/b54479b3-acb8-426b-b442-923a2630f356" width="700">
</p>

#### 🧬 ERD Diagram (Database Per Service)
마이크로서비스 원칙에 따라 각 도메인별로 물리적인 데이터베이스(스키마)를 완벽히 분리(Database per Service)하여 서비스 간의 결합도를 낮췄습니다.
<p align="center">
  <img src="https://github.com/user-attachments/assets/dc2c8022-e769-467a-ae30-b861995f3d95" width="800">
</p>


#### 💻 Infra Diagram

<p align="center">
  <img src="https://github.com/user-attachments/assets/f789fcb7-cd2a-48fd-bcba-da32a74efcc6" width="600">
</p>


| Layer             | 요소                                       | 서브넷          |
| ----------------- | ---------------------------------------- | ------------ |
| Ingress           | ALB → API Gateway                        | Public       |
| Service Discovery | Eureka Server                            | Public       |
| Application       | MSA Container (6 Services)               | Private      |
| Data              | Kafka Cluster, Redis, PostgreSQL (6 DBs) | Private Data |
| Authentication    | Keycloak                                 |       |

#### 🛡️ Network & Security (Firewall)
* **API Gateway 전역 인증:** `JwtAuthenticationFilter`를 통해 모든 외부 요청의 Keycloak JWT 토큰을 검증합니다.
* **망 분리:** 외부망(Public Subnet)에서는 API Gateway(8080)만 접근이 가능하며, 실제 비즈니스 로직을 처리하는 6개의 MSA 컨테이너와 DB/Kafka 인프라는 내부망(Private Subnet)으로 철저히 격리했습니다.

<br>

> 인바운드 정책

| 대상           | 허용 포트       | 소스                 | 설명                        |
| ------------ | ----------- | ------------------ | ------------------------- |
| ALB          | 80, 443     | 0.0.0.0/0          | 외부 HTTPS 트래픽 수신           |
| API Gateway  | 8080        | ALB SG             | ALB를 통해서만 접근 허용           |
| MSA Service  | 8081 ~ 8086 | Gateway SG         | Gateway 및 내부 서비스만 접근 가능   |
| Kafka Broker | 9092        | App SG             | 애플리케이션 서브넷 내부 통신만 허용      |
| Redis        | 6379        | App SG             | 애플리케이션 서브넷 내부 통신만 허용      |
| PostgreSQL   | 5432        | App SG             | 애플리케이션 서브넷 내부 통신만 허용      |
| Keycloak     | 8443        | Gateway SG, App SG | JWT 공개키 조회 및 토큰 검증용 접근 허용 |

> 아웃바운드 정책

| 대상                        | 포트                 | 설명                            |
| ------------------------- | ------------------ | ----------------------------- |
| MSA → 외부 인터넷              | 차단                 | 외부 네트워크 직접 접근 제한              |
| Slack Service → Slack API | 443                | Slack 알림 전송 허용 (화이트리스트 적용 가능) |
| 내부 서비스 간 통신               | FeignClient, Kafka | 서비스 간 통신 허용                   |

<br>

## ✨ Core Features (주요 기능)

### 1. 🔐 중앙 집중식 인증 및 다중 권한 제어 (User & Auth)
* **Keycloak 기반 SSO 및 IAM:** 인증과 인가 책임을 `Keycloak`으로 분리하여 각 마이크로서비스는 비즈니스 로직에만 집중하도록 아키텍처를 설계했습니다.
* **데이터 격리 (Data Isolation):** `HUB_MANAGER` 권한을 가진 유저는 시스템 전역 데이터가 아닌, **자신이 소속된 허브의 기사님/유저/주문 목록만 조회할 수 있도록** API 단에서 강력한 데이터 격리 정책을 적용했습니다.

### 2. 🚚 라운드 로빈(Round-Robin) 기반 배송 기사 자동 배정
* 특정 허브에 신규 배송 건이 발생 시, **대기 중(`WAITING`)인 기사님들 중 '마지막 배정 시각(`lastAssignedAt`)'이 가장 오래된 순서(오름차순)**로 쿼리하여 기사님을 공평하게 자동 배정합니다.
* 외부 배달 서비스(Delivery Service)에서 FeignClient로 배정을 요청하면, 즉시 배정 처리 후 결과를 반환합니다.

### 3. 📬 Kafka 기반 비동기 알림 및 AI 연동
* **이벤트 주도 아키텍처 (EDA):** 배송 기사 배정이 완료되면 `delivery-manager.events` 토픽으로 `DeliveryAssigned` 이벤트를 발행합니다.
* 알림 서비스(Notification)는 이를 Consume하여 슬랙(Slack)으로 비동기 발송을 진행하며, AI 서비스는 해당 이벤트를 트리거로 Gemini API를 호출해 예상 도착 시간을 산출합니다. 핵심 트랜잭션의 성능 저하를 막고 장애를 격리했습니다.

<br>

## 💥 Troubleshooting (트러블슈팅)

### Issue 1. 분산 환경에서의 보상 트랜잭션(Compensating Transaction) 처리
* **배경:** `User Service`에서 회원가입 처리 시 Keycloak에 유저를 먼저 생성하고, 이후 내부 DB에 유저 정보를 저장하는 과정에서 예외(DB 에러 또는 입력된 Hub ID 무효)가 발생하면 Keycloak에만 유저가 남는 **고아 데이터(Orphan Data)** 현상이 발생했습니다.
* **해결 방안:** 단순 `@Transactional`로는 외부 시스템(Keycloak)의 롤백이 불가능함을 인지하고, `catch` 블록 내에서 `keycloakClient.deleteUser()` API를 명시적으로 호출하는 **프로그래밍적 보상 트랜잭션 패턴**을 적용하여 분산 시스템 환경에서의 데이터 정합성을 확보했습니다.

### Issue 2. 모노레포 CI 환경에서의 빌드 캐시 충돌 및 테스트 고도화
* **배경:** MSA 모노레포 구조에서 깃허브 액션(GitHub Actions) CI 구동 시, 삭제된 파일의 `.class` 캐시가 남아 `ConflictingBeanDefinitionException`을 유발하고, 빈 깡통 서버에서 `@SpringBootTest`가 DB 연결을 시도해 CI가 터지는 문제가 발생했습니다.
* **해결 방안:** 1. `.github/workflows`의 테스트 스텝에 `./gradlew clean test` 명령어를 적용해 빌드 캐시 유령(Ghost Class) 문제를 완전히 해결했습니다.
  2. 무거운 컨텍스트 로딩 테스트를 삭제하고, **JUnit5 + Mockito 기반의 순수 단위 테스트(Unit Test)**로 전면 개편하여 CI 빌드 속도를 단축하고 비즈니스 로직(데이터 격리, 보상 트랜잭션 호출 등)의 엣지 케이스 검증 커버리지(Jacoco)를 대폭 끌어올렸습니다.

<br>

## 🚀 서비스 구성 및 실행 방법
### Prerequisites
  * Java 21
  * Docker & Docker Compose

### 인프라 원클릭 셋업 (Infra Setup)
프로젝트 루트 디렉토리에서 아래 명령어를 통해 PostgreSQL(도메인별 스키마 분리), Redis, Kafka, Keycloak 인프라를 한 번에 기동합니다.
</br>
<img width="453" height="75" alt="image" src="https://github.com/user-attachments/assets/058f64c6-d9a9-4a38-bff2-93e6c71c6b38" />

### 서비스 실행 (Service Run)
Eureka Server와 API Gateway를 먼저 기동한 후, 비즈니스 마이크로서비스들을 순차적으로 실행합니다.
</br>
<img width="364" height="237" alt="image" src="https://github.com/user-attachments/assets/37424a44-6ac9-4641-ac4a-4b5987bc8a74" />



