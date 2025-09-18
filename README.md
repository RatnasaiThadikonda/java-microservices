# E-Commerce Microservices Project

A **Java Spring Boot microservices project** implementing an **e-commerce order system** with **Kafka-based event-driven architecture**.  
The system consists of multiple microservices communicating asynchronously using Kafka topics.

---

## **Project Overview**

**Services included:**

| Service | Responsibility |
|---------|----------------|
| **User Service** | Manage users, create user events |
| **Product Service** | Manage products, update inventory based on orders |
| **Order Service** | Create orders and publish order events |
| **Notification Service** | Send notifications (email/SMS) on order events |
| **Kafka + Zookeeper** | Messaging backbone for event-driven communication |

**Workflow:**
1. User places an order via **Order Service**.
2. **Order Service** publishes an `order.created` event to Kafka.
3. **Product Service** consumes the event → updates inventory.
4. **Notification Service** consumes the event → sends confirmation.
5. Microservices remain decoupled and scalable.

---

## **Requirements**

- Java 21  
- Maven 3.9.9   
- Docker 20+ and Docker Compose 3.8+  
- IDE (IntelliJ IDEA, VS Code, or Eclipse)  
- Optional: Postman for API testing  

---

## **Setup and Run**

### **1️⃣ Clone the repository**
```bash
git clone <repository-url>
cd e-commerce-microservices
