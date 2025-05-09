# RC Photography Web Application

A Spring Boot web application to manage a professional photography portfolio with features like photo upload, edit/delete, and message handling.

## 🚀 Features

### 📷 Photo Management
- Upload multiple photos with title and description
- Edit photo metadata and image
- Delete photos
- Pagination for managing large photo galleries
- View recent photo uploads on the dashboard

### 💬 Contact Messages
- View recent messages sent via the public site
- Organized in descending order of date sent

### 📊 Admin Dashboard
- See total photo count
- Recent photo uploads
- Recent contact messages

### 🖼️ Image Handling
- Image previews in photo lists
- Server-side image upload with timestamp-based filenames
- Files are stored in a local directory (`D:/uploads` by default)

### 🔒 Secure Admin Panel *(future scope)*
- Admin-only access routes (add basic auth/session-based login later)

---

## 🧰 Tech Stack

- **Backend**: Java, Spring Boot, Spring MVC, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5, AOS.js
- **Database**: MySQL (or H2 for testing)
- **File Uploads**: Multipart handling with image previews

---


