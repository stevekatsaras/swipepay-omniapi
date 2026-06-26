# SwipePay

SwipePay is a full-stack fintech platform built as a personal project to prototype a commercial-grade payment processing and crypto wallet ecosystem. It covers two product surfaces: a merchant-facing payment gateway and a consumer-facing mobile wallet that supports bill payment via digital currency.

---

## Architecture

```
MERCHANT SURFACE
┌─────────────────────┐     ┌─────────────────────┐
│  swipepay-          │────▶│  swipepay-omniapi   │
│  client-desk        │     │  (payment gateway   │
│  (merchant portal)  │     │   API)              │
└─────────────────────┘     └────────┬────────────┘
                                      │
                          ┌───────────▼───────────┐
                          │  swipepay-crypto-     │
                          │  service              │
                          │  (AWS KMS encryption) │
                          └───────────┬───────────┘
                                      │
                          ┌───────────▼───────────┐
                          │  swipepay-crypto-lib  │
                          │  (KMS client library) │
                          └───────────────────────┘

CONSUMER SURFACE
┌─────────────────────┐     ┌─────────────────────┐
│  swipepay-wallet-   │────▶│  swipepay-wallet-   │
│  mobile-app         │     │  api                │
│  (React Native)     │     │  (bills + crypto    │
└─────────────────────┘     │   payments API)     │
                             └─────────────────────┘
```

---

## Services

| Repository | Description |
|---|---|
| [swipepay-omniapi](https://github.com/stevekatsaras/swipepay-omniapi) | Core payment gateway API. Supports bank account and credit card management (with tokenisation), customer management, and transaction processing (payment, pre-auth, refund, query). Backed by MySQL Aurora with distributed read/write nodes. |
| [swipepay-crypto-service](https://github.com/stevekatsaras/swipepay-crypto-service) | Microservice for cryptographic operations — key generation and data encryption/decryption — backed by AWS Key Management Service (KMS). Used to secure sensitive payment data across the platform. |
| [swipepay-crypto-lib](https://github.com/stevekatsaras/swipepay-crypto-lib) | Shared Java library encapsulating AWS KMS connectivity. Used internally by `swipepay-crypto-service`. |
| [swipepay-client-desk](https://github.com/stevekatsaras/swipepay-client-desk) | Merchant-facing management portal. Allows merchants to manage their account, view transactions, and administer billing. Integrates with `swipepay-crypto-service` for data protection. |
| [swipepay-wallet-api](https://github.com/stevekatsaras/swipepay-wallet-api) | REST API for the consumer wallet app. Manages user bills and utilities, and supports payment via digital currency by integrating with crypto networks and exchanges. |
| [swipepay-wallet-mobile-app](https://github.com/stevekatsaras/swipepay-wallet-mobile-app) | React Native mobile app for consumers. Allows users to manage bills and utilities and pay them using digital currency. Communicates exclusively with `swipepay-wallet-api`. |

---

## Tech Stack

- **Languages:** Java (Spring Boot), JavaScript (React Native)
- **Payment Processing:** Credit card tokenisation, pre-auth, refund flows
- **Cryptography:** AWS Key Management Service (KMS), AES-128 encryption
- **Database:** MariaDB / MySQL Aurora (with replication and Multi-AZ failover)
- **Mobile:** React Native 0.59, React Navigation
- **Infrastructure:** AWS (RDS, KMS, ECS)

---

## Status

Personal prototype project. Core payment gateway and wallet API are implemented end-to-end. Crypto payment integration in the wallet is a work in progress. Not in production.
