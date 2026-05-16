# MyShop

MyShop là một hệ thống quản lý bán hàng (e-commerce management system) đơn giản được xây dựng bằng Spring Boot và Thymeleaf.

## Chức năng

### Người dùng (User)

Ở giao diện mặc định, hệ thống sẽ hoạt động với vai trò người dùng thông thường. Người dùng có thể:

* Xem danh sách sản phẩm
* Tìm kiếm và lựa chọn sản phẩm
* Tạo đơn hàng

---

### Quản trị viên (Admin)

Để sử dụng các chức năng quản trị, cần đăng nhập bằng tài khoản admin.

Chức năng quản trị bao gồm:

#### Quản lý sản phẩm

* Xem danh sách sản phẩm
* Thêm sản phẩm
* Cập nhật sản phẩm
* Xóa sản phẩm
* Xem chi tiết sản phẩm

#### Quản lý đơn hàng

* Xem danh sách đơn hàng
* Xem chi tiết đơn hàng

#### Khác

* Phân trang và sắp xếp sản phẩm
* Xác thực và phân quyền bằng Spring Security

---

# Công nghệ sử dụng

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

# Chạy dự án

## Khởi động database

```bash
docker compose up -d
```

## Chạy ứng dụng

```bash
./mvnw spring-boot:run
```

Hoặc chạy trực tiếp bằng IntelliJ IDEA.

---

# Tài khoản Admin

```text
username: admin
password: 123456
```

---

# Các đường dẫn chính

## Trang người dùng

```text
http://localhost:8080/user/products
```

## Trang quản lý sản phẩm (Admin)

```text
http://localhost:8080/admin/products
```

## Trang quản lý đơn hàng (Admin)

```text
http://localhost:8080/admin/orders
```

## Đăng nhập

```text
http://localhost:8080/login
```

---

# Bảo mật

* Xác thực theo session bằng Spring Security
* Phân quyền theo role
* Chỉ tài khoản ADMIN mới có thể truy cập `/admin/**`

---

# Hướng phát triển trong tương lai

* Upload hình ảnh sản phẩm
* Giỏ hàng
* Quản lý trạng thái đơn hàng
* REST API + React frontend
* JWT Authentication
* Deploy bằng Docker

---

# Tác giả

Dự án được phát triển cho bài đánh giá kỹ thuật (technical assessment project).