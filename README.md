# 🔐 OAuth2 + JWT Authentication System (Spring Boot 3.x)

<p align="center">
  <b>A Production-Grade Backend Reference for Stateless Authentication & Authorization</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/SpringBoot-3.x-brightgreen?style=for-the-badge&logo=springboot">
  <img src="https://img.shields.io/badge/Security-OAuth2%20%2B%20JWT-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Architecture-Stateless-critical?style=for-the-badge">
  <img src="https://img.shields.io/badge/Database-MySQL-blue?style=for-the-badge&logo=mysql">
</p>

---

## 📌 Overview
This repository serves as a **high-signal technical reference** for implementing modern, secure, and scalable authentication. It demonstrates a transition from traditional session-based security to a **Stateless OAuth2 + JWT architecture**.

### 🎯 Key Problems Solved
*   **Decoupled Identity:** Uses Google OAuth to handle user identity, removing the need to manage sensitive passwords locally.
*   **Scalability:** Implements stateless JWTs, allowing the backend to scale horizontally without session-sharing overhead.
*   **Security Context:** Deep integration with the Spring Security Filter Chain for robust API protection.

---

## 🧠 System Architecture



### 🔐 The Authentication Flow
1.  **Challenge:** User attempts to access a protected endpoint (e.g., `/home`).
2.  **Redirect:** Spring Security redirects the user to the **Google OAuth2** consent screen.
3.  **Callback:** Upon successful login, Google returns user details to our `OAuth2SuccessHandler`.
4.  **Persistence:** The system checks if the user exists in **MySQL** via the `UserService`; if not, a new record is created.
5.  **Issuance:** The `JwtUtil` generates a signed **JWT Token** containing the user's email and roles.
6.  **Handshake:** The token is returned to the client (typically via a Cookie or URL parameter).

---

## 🛡️ Authorization Filter Logic



Once the user has a token, every subsequent request follows this high-speed validation path:

*   **Intercept:** `JwtFilter` intercepts the incoming request.
*   **Extract:** It pulls the token from the `Authorization: Bearer <token>` header.
*   **Validate:** `JwtUtil` verifies the signature and expiration.
*   **Inject:** If valid, the user's details are injected into the `SecurityContextHolder`, allowing the request to reach the Controller.

---

## ⚙️ Tech Stack & Dependencies
*   **Language:** Java 17+
*   **Framework:** Spring Boot 3.x (Jakarta EE)
*   **Security:** Spring Security + OAuth2 Client
*   **Tokens:** JJWT (Java JWT library)
*   **Database:** MySQL
*   **Utilities:** Lombok, Spring Data JPA

---

## 📂 Project Structure
src/main/java/com/project/
├── config/             # SecurityConfig & OAuth2SuccessHandler
├── security/           # Custom JwtFilter
├── util/               # JwtUtil (Token Generation & Validation)
├── controller/         # Secured REST Endpoints
├── service/            # Business Logic & User Management
├── repository/         # Spring Data JPA Interfaces
└── entity/             # JPA Entities (User Model)

---

## 🔐 Authentication Flow (OAuth2)

<img width="747" height="562" alt="image" src="https://github.com/user-attachments/assets/d51cc36f-96e9-4549-ae13-ede238b4240b" />

Flow Breakdown
User hits protected endpoint
Redirected to Google OAuth
User authenticates
Google returns user details
User is created/fetched from DB
JWT token generated
Token returned to client

---

## 🛡️ Authorization Flow (JWT)

<img width="869" height="432" alt="image" src="https://github.com/user-attachments/assets/909d512e-80f2-4411-9ac4-8a4552edf27a" />


