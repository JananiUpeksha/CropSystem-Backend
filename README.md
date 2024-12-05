

---

# 🌾 Crop Management System

The Crop Management System is an all-in-one solution designed to streamline agricultural activities. This platform provides tools to efficiently manage fields, crops, equipment, vehicles, staff, and logs, ensuring optimal farm productivity and data-driven decision-making.

## 🔗 Access the Crop Management System

**Frontend**:  
Access the user-friendly interface for the Crop Management System:  
[Crop System Frontend](https://github.com/JananiUpeksha/CropSystem-Frontend)

**Backend**:  
Explore the secure REST API endpoints:  
[Crop System Backend](https://github.com/JananiUpeksha/CropSystem-Backend)

---

## 🚀 Features

### Core Functionalities

1. **Field Management**:  
   Manage fields with detailed information, including images, size, location, and assigned crops.

2. **Crop Management**:  
   Track crop data such as type, growth stage, and field allocation.

3. **Staff Management**:  
   Oversee staff roles, assignments, and responsibilities.

4. **Equipment Management**:  
   Assign, monitor, and maintain farm equipment throughout its lifecycle.

5. **Vehicle Management**:  
   Manage vehicle assignments, maintenance schedules, and availability.

6. **Monitoring Logs**:  
   Record important observations and updates on various farm entities, including crops and equipment.

7. **Role-Based Access Control**:  
   Empower users with custom permissions based on the **Manager** role.

---

## 🌟 Additional Highlights

- **Single Page Application (SPA)** for a seamless user experience.
- **Spring Boot Backend** with robust authentication and data processing.
- **JWT-Based Authentication** for secure access.
- **MySQL Integration** for relational data management.

---

## 🛠️ Tech Stack

### Frontend  
- **HTML5**, **CSS3**, **JavaScript**  
- Frameworks: **Bootstrap** for responsive design  
- API Integration: **AJAX** & **Fetch API**

### Backend  
- **Spring Boot** for RESTful APIs  
- **Hibernate** for ORM (Object-Relational Mapping)  
- **MySQL** for data persistence  
- **JWT** for secure authentication

---

## 📋 Installation Guide

### Prerequisites

- **Java Development Kit (JDK)** version 17 or higher.
- **MySQL Server** installed locally or remotely.
- **Postman** (optional, for API testing).

---

### Steps

#### Backend Setup

1. Clone the repository:

    ```bash  
    git clone https://github.com/JananiUpeksha/CropSystem-Backend.git  
    cd CropSystem-Backend  
    ```

2. Configure the database:

    Update `application.properties` with your MySQL credentials:

    ```properties  
    spring.datasource.url=jdbc:mysql://localhost:5050/cropMng
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

3. Start the backend server:

    ```bash  
    ./mvnw spring-boot:run
    ```

#### Frontend Setup

1. Navigate to the frontend folder:

    ```bash  
    cd CropSystem-Frontend
    ```

2. Install dependencies:

    ```bash  
    npm install
    ```

3. Start the frontend development server:

    ```bash  
    npm start
    ```

Access the application at:  
- **Frontend**: `http://localhost:3000`  
- **Backend**: `http://localhost:5050/cropMng/api/v1`

---

## 🔐 Role-Based Permissions

| Role        | Permissions                                                                                       |  
|-------------|---------------------------------------------------------------------------------------------------|  
| **Manager** | Full access to CRUD operations for staff entities.                                                  |

---

## 🗃️ Database Relationships

- **Fields & Crops**: Many-to-Many relationship.
- **Fields & Equipment**: One-to-Many relationship.
- **Staff & Vehicles**: One-to-Many relationship.
- **Monitoring Logs**: Tracks updates for all farm entities.

---

## 📄 API Highlights

### Base URL  

`http://localhost:5050/cropMng/api/v1/`

### Sample Endpoints

1. **User Signup**:  
   `POST /auth/signup`

2. **Get All Fields**:  
   `GET /fields`

3. **Add New Crop**:  
   `POST /crops`

4. **Update Equipment**:  
   `PUT /equipments/{equipmentId}`

For complete API details, refer to the [API Documentation](https://documenter.getpostman.com/view/36189377/2sAYBbcTZz).

---

## 📃 License

This project is licensed under the **MIT License**. For details, see the LICENSE file.

---


