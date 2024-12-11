Developed a microservice-based ecommerce project using Spring MVC and Spring Boot.

User Authentication Service: https://github.com/MANOJSB5112/UserAuthJWT
Implemented JWT-based authentication and authorization with Spring Security, securely persisting JWT tokens in MySQL for user session management.
Encrypted user passwords using the Bcrypt algorithm to enhance security.

Inventory And Order Service: https://github.com/MANOJSB5112/InventoryAndOrderService
Created RESTful APIs and implemented end-to-end inventory , cart, and order management system.
Integrated scalable AWS RDS with Spring Boot application to provide seamless database connectivity, using Hibernate/JPA for ORM (Object-Relational Mapping).
Optimized API response time from ~1100 ms to ~20 ms by utilizing caching for static data with Redis Cache.

Notification Service: https://github.com/MANOJSB5112/NotificationService
Implemented AWS SNS (Simple Notification Service) and AWS SQS (Simple Queue Service) to handle asynchronous task processing and decouple services, ensuring scalable and resilient inter-service communication.
Automated email notifications by integrating AWS SES (Simple Email Service), facilitating real-time updates to users.

Payment Service: https://github.com/MANOJSB5112/PaymentService
Incorporated Razorpay payment gateway into the application, providing secure and seamless third-party payment processing.
Developed an API for generating payment links, allowing users to easily initiate payments.
