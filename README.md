# Draw-It-or-Lose-It
"A Java-based game management app with REST API, user authentication, and role-based access control."



📌 Overview
The Game App is an interactive application designed to provide a seamless gaming experience while showcasing secure REST API integration, user authentication, and role-based authorization. Built using Java, it follows object-oriented principles and leverages RESTful services for smooth client-server communication.

🚀 Features
User Authentication & Authorization

BasicAuth implementation with role-based access control

Game Management

Create, view, and manage games, players, and teams

RESTful API

Endpoints for handling game user data

Client-Server Integration

Jersey Client for consuming REST services

Secure Communication

Enforced permissions for specific user roles

🛠️ Technologies Used
Java SE 11+

Spring Boot / Jersey (JAX-RS) (or specify exact framework used)

Maven/Gradle for dependency management

HTTP Basic Authentication

JSON for data exchange

📂 Project Structure
bash
Copy
Edit
├── src/
│   ├── main/java/com/gameapp/
│   │   ├── GameAuthApplication.java      # Main application class
│   │   ├── controller/
│   │   │   ├── GameUserRESTController.java
│   │   │   ├── RESTClientController.java
│   │   └── model/
│   │       ├── GameUser.java
│   │       ├── Role.java
│   │
│   └── test/java/...                     # Unit tests
├── pom.xml                                # Maven configuration
└── README.md                              # Project documentation
⚙️ Installation & Setup
Clone the repository

bash
Copy
Edit
git clone [https://github.com/yourusername/game-app.git](https://github.com/EricaBoterf/Draw-It-or-Lose-It.git)
cd game-app
Build the project

bash
Copy
Edit
mvn clean install
Run the application

bash
Copy
Edit
mvn spring-boot:run
Access API Endpoints

Base URL: http://localhost:8080/api

Example: GET /api/gameusers

🔐 Authentication
Default credentials:

Username: admin

Password: password123

Supports BasicAuth for all secured endpoints.

📡 API Endpoints
Method	Endpoint	Description
GET	/gameusers	Fetch all game users
POST	/gameusers	Add a new game user
GET	/gameusers/{id}	Fetch user by ID

(Add more as needed)

✅ Future Enhancements
JWT-based authentication

Front-end integration

Additional roles and permissions

Database persistence (currently in-memory)

📄 License
This project is licensed under the MIT License. See the LICENSE file for details.
