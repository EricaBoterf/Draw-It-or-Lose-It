🎨 Draw It or Lose It
📌 Overview
Draw It or Lose It is a Java-based multiplayer drawing game where players guess each other’s drawings in real-time. The app includes secure user authentication, RESTful APIs, and role-based access control.

🚀 Features
Player registration and login with authentication

REST API for managing users and game sessions

Role-based authorization (Admin, Player)

Real-time drawing and guessing features (future enhancement)

🛠️ Technologies Used
Java 11+

Spring Boot / Jersey

Maven

Basic Authentication


⚙️ Setup Instructions
Clone the repository:



git clone https://github.com/yourusername/Draw-It-or-Lose-It.git
cd Draw-It-or-Lose-It

Build and run:

mvn clean install
mvn spring-boot:run

Access the API at:

http://localhost:8080/gameusers

🔐 Authentication
Username: admin

Password: password123

✅ Future Enhancements
WebSocket for real-time gameplay

Front-end UI with drawing board

Leaderboards and scoring system

📄 License
MIT License
