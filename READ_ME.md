# MyShop

MyShop is a simple e-commerce management system built with Spring Boot and Thymeleaf.

## Features

### Admin

* Manage products

  * View product list
  * Create product
  * Update product
  * Delete product
  * View product detail
* Manage orders

  * View order list
  * View order detail
* Product sorting and pagination
* Authentication and authorization with Spring Security

### User

* Browse products
* Search and select products
* Create orders

---

# Technologies

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security
* Thymeleaf
* MySQL
* Docker Compose
* Bootstrap 5
* Maven

---

# Run Project

## Start database

```bash id="fpy5j3"
docker compose up -d
```

## Run application

```bash id="9v38e7"
./mvnw spring-boot:run
```

or run directly from IntelliJ IDEA.

---

# Admin Account

```text id="k9v4oq"
username: admin
password: 123456
```

---

# Main URLs

## User Shop

```text id="8z45ml"
http://localhost:8080/user/products
```

## Admin Products

```text id="3n86b2"
http://localhost:8080/admin/products
```

## Admin Orders

```text id="g3svl1"
http://localhost:8080/admin/orders
```

## Login

```text id="c6i3em"
http://localhost:8080/login
```

---

# Security

* Session-based authentication using Spring Security
* Role-based authorization
* Only ADMIN can access `/admin/**`

---

# Future Improvements

* Product image upload
* Shopping cart
* Order status management
* REST API + React frontend
* JWT authentication
* Deploy with Docker

---

# Author

Developed for technical assessment project.
