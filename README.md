swipepay-omniapi
----------------
An Omni API that allows SwipePay customers to perform a variety of e-commerce payment gateway functions. These include:
1. Managing bank accounts (Add, Edit, Get, List, Lookup)
2. Managing credit cards (Add, Edit, Get, List, Lookup) - tokenization capabilities
3. Crypto services (Generate key, Encrypt, Decrypt)
4. Manage customer information (Add, Edit, Get, List)
5. Perform transaction payments (ccPayment, ccPreAuth, ccRefund, ccQuery)

If using MySQL Aurora,
NOTE: Aurora supports distributed READ/WRITE nodes and Multi AZ failover

SPRING_DATASOURCE_URL = jdbc:mariadb:aurora://<CLUSTER_ENDPOINT>:3306/omnihub

If using MariaDB RDS,
NOTE: RDS supports Single AZ or Multi AZ failover

SPRING_DATASOURCE_URL = jdbc:mariadb://<RDS_DNS_ENDPOINT>:3306/omnihub



----------
PRODUCTION
----------
OMNIAPI_CRYPTO_AUTH-ACCESS-KEY = 
OMNIAPI_CRYPTO_AUTH-SECRET-KEY = 
OMNIAPI_CRYPTO_KMS-CMK-REGION = us-west-2
OMNIAPI_CRYPTO_KMS-CMK-ID = arn:aws:kms:us-west-2:490873020494:key/0524bba3-83a8-440a-8f1f-49bc33375778

-----------
DEVELOPMENT
-----------
OMNIAPI_CRYPTO_AUTH-ACCESS-KEY = 
OMNIAPI_CRYPTO_AUTH-SECRET-KEY = 
OMNIAPI_CRYPTO_KMS-CMK-REGION = us-east-1
OMNIAPI_CRYPTO_KMS-CMK-ID = arn:aws:kms:us-east-1:294254383015:key/73e8747d-622a-4e52-a522-424a544ec7f8
OMNIAPI_PAYMENT_GATEWAY-FORCE-SIMULATION = true

---
SIT
---
OMNIAPI_CRYPTO_AUTH-ACCESS-KEY = 
OMNIAPI_CRYPTO_AUTH-SECRET-KEY = 
OMNIAPI_CRYPTO_KMS-CMK-REGION = us-east-2
OMNIAPI_CRYPTO_KMS-CMK-ID = arn:aws:kms:us-east-2:294254383015:key/cc4ca777-cb7c-43fd-9a12-e064ecd15fa5
OMNIAPI_PAYMENT_GATEWAY-FORCE-SIMULATION = true

-------
STAGING
-------
OMNIAPI_CRYPTO_AUTH-ACCESS-KEY = 
OMNIAPI_CRYPTO_AUTH-SECRET-KEY = 
OMNIAPI_CRYPTO_KMS-CMK-REGION = us-west-2
OMNIAPI_CRYPTO_KMS-CMK-ID = arn:aws:kms:us-west-2:294254383015:key/9146d650-5dfa-41a2-a755-1cacd98d816e
OMNIAPI_PAYMENT_GATEWAY-FORCE-SIMULATION = true






TODO
----
fix the status codes (properly)

repair the payments API (may need refactoring)
payment/preauth - pay by customer
payment/preauth - tokenise on successful tx

explore periodic/triggered payments (securepay docs)

copy omnihub.io hosted zone from swipepay to omnihub
buy cheap domain for omnihub-sanbox
inspect ssl certs for omnihub.io

simulator - how to simulate a timeout ???
caching merchant data (update every so often)
fraudguard rules
batch payments

audit trail
email merchant/cardholder option
2 factor auth - setting on the merchant against the cardholder!
error handling of api calls downstream
