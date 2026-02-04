# 🛒 Webstore Backend

Spring Boot alapú webáruház backend, **checkout–payment szétválasztott folyamattal**,  
inventory foglalással, **outbox patternnel**, RabbitMQ integrációval.

---

## 🧱 Architektúra alapelvek

- **JPA + Flyway** – adatbázis az igazság forrása
- **Inventory safe** – pessimistic locking
- **Outbox pattern** – garantált eseményküldés
- **Idempotens payment callback**
- **Checkout ≠ Payment ≠ Finalization**

---

## 🧭 Rendelési folyamat (Order lifecycle)

### 1️⃣ Kosár (DRAFT)
- nincs inventory módosítás
- user1 szerkeszthet

---

### 2️⃣ Checkout véglegesítés
**Service:** `CheckoutConfirmService`
- árak fixálása
- inventory foglalás (`reserved += qty`)
- frontend végleges összeg megjelenítése

---

### 3️⃣ Fizetés indítása
**Service:** `PaymentStartService`
- fizetési mód kiválasztás
- redirect payment providerhez
- inventory változatlan

---

### 4️⃣ Fizetés SIKERES (callback)
**Service:** `PaymentCallbackService` → `OrderService.finalizeOrder()`

- inventory véglegesítés
    - `quantity -= qty`
    - `reserved -= qty`
- outbox event létrejön:
    - `ORDER_PAID`

---

### 5️⃣ Aszinkron feldolgozás
**Service:** `OutboxPublisherService`

- outbox → RabbitMQ
- számlázás
- email
- shipping

---

### 6️⃣ Timeout (30 perc)
**Job:** `PaymentTimeoutJob`

- inventory foglalás visszaengedése
- nincs outbox event

---

## 🔁 Idempotencia

### Domain szinten
- `finalizeOrder()` **csak egyszer hajthat végre üzleti hatást**

### API szinten
- payment callback többszöri hívása:
    - `PAID` → no-op
    - retry safe

---

## 📦 Outbox Pattern

- event **ugyanabban a tranzakcióban**, mint a domain state change
- RabbitMQ csak delivery mechanizmus
- nincs event loss
- restart-safe

---

## 🧩 Technológiai stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security (JWT)
- PostgreSQL
- Flyway
- RabbitMQ

---

## 🚀 Indítás

```bash
mvn clean spring-boot:run





