# 🏏 Online Cricket Score

A Java-based web application that provides **live cricket scores, match highlights, match history, and upcoming match information** through a live cricket API. The application also includes user authentication, admin management features, and password-reset functionality.

---

## 📌 Project Overview

**Online Cricket Score** is a dynamic cricket web application developed using **Java, Servlets, JSP, MySQL, HTML, CSS, and JavaScript**.

The application integrates a live cricket API to retrieve current match information and provides users with an easy way to view live scores, highlights, previous matches, and upcoming match details.

It also includes an admin module for managing application data and a password-reset feature using email functionality.

---

## ✨ Features

### 👤 User Features

* 🔐 User registration and login
* 🏏 View live cricket scores
* 📊 View current match details
* 🎥 View match highlights
* 📅 View upcoming matches
* 📜 View match history
* 🔑 Password reset through email
* 🚪 Secure logout functionality

### 👨‍💼 Admin Features

* 🔐 Admin authentication
* ➕ Add cricket-related records
* ✏️ Update existing records
* 🗑️ Delete records
* 👀 View and manage application data
* 📊 Manage match-related information

### 🌐 API Integration

* Integrated a third-party cricket API for live match information
* Fetches current match data dynamically
* Processes API responses and displays relevant cricket information
* Handles API-based data retrieval from the Java backend

---

## 🛠️ Technologies Used

| Technology         | Purpose                            |
| ------------------ | ---------------------------------- |
| **Java**           | Backend application development    |
| **Servlets**       | Request handling and backend logic |
| **JSP**            | Dynamic web pages                  |
| **HTML5**          | Page structure                     |
| **CSS3**           | Styling and layout                 |
| **JavaScript**     | Client-side functionality          |
| **MySQL**          | Database management                |
| **JDBC**           | Java–MySQL database connectivity   |
| **Tomcat 11.0.15** | Application server                 |
| **NetBeans 28**    | Development environment            |
| **JDK 25**         | Java development                   |

---

## 🏗️ Application Architecture

The project follows a traditional Java web application architecture:

```text
Browser
   │
   ▼
JSP / HTML / CSS / JavaScript
   │
   ▼
Java Servlets
   │
   ├──────────────► Cricket API
   │                    │
   │                    ▼
   │               Live Match Data
   │
   ▼
JDBC
   │
   ▼
MySQL Database
```

### Main Layers

* **Presentation Layer** — JSP, HTML, CSS, JavaScript
* **Controller Layer** — Java Servlets
* **Database Layer** — JDBC and MySQL
* **External API Layer** — Cricket API integration

---

## 🔄 How It Works

1. The user opens the application in a web browser.
2. The application sends requests to the appropriate Java Servlet.
3. For live cricket information, the backend communicates with the cricket API.
4. The API response is processed by the Java application.
5. Relevant match information is displayed through JSP pages.
6. User-related and application data is stored/retrieved using MySQL and JDBC.
7. Admin users can perform management operations through the admin module.

---

## 🔌 API Integration

The application uses a third-party cricket API to retrieve live cricket information.

Keep the API key outside the source code and configure it locally.

For example:

```java
String apiKey = System.getenv("CRICKET_API_KEY");
```

Then configure the environment variable on your local machine:

```text
CRICKET_API_KEY=YOUR_API_KEY
```

> Replace `YOUR_API_KEY` with your own API key locally
---

## 🗄️ Database

The application uses **MySQL** for persistent data storage.

Database responsibilities include:

* User account information
* Authentication-related data
* Application records
* Admin-managed data
* Match-related information where applicable

### Database Configuration

Update the database configuration according to your local environment:

```java
String url = "jdbc:mysql://localhost:3306/your_database";
String username = "your_username";
String password = "your_password";
```

---

## 📁 Project Structure

A typical project structure is organized around Java Servlets, JSP pages, database connectivity, and web resources:

```text
OnlineCricketScore/
│
├── src/
│   └── java/
│       └── ...
│
├── Web Pages/
│   ├── JSP pages
│   ├── CSS
│   ├── JavaScript
│   └── images
│
├── WEB-INF/
│   └── web.xml
│
├── nbproject/
│
├── build.xml
│
└── README.md
```

> The exact package and file names may vary depending on the NetBeans project configuration.

---

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

* **JDK 25**
* **Apache NetBeans 28**
* **Apache Tomcat 11.0.15**
* **MySQL**
* **MySQL JDBC Driver**
* A valid cricket API key

---

### 1. Clone the Repository

```bash
git clone https://github.com/vickykushwah57/live-cricket-score.git
```

### 2. Open the Project

Open **Apache NetBeans 28** and select:

```text
File → Open Project
```

Then select the cloned project directory.

### 3. Configure MySQL

Create the required database in MySQL and update the database connection details in the project according to your local environment.

### 4. Configure the Cricket API

Configure your API key using an environment variable or another local configuration method.

### 5. Configure Tomcat

Add **Apache Tomcat 11.0.15** as the server in NetBeans.

### 6. Run the Application

Run the project from NetBeans.

The application will be deployed on the configured Tomcat server.

---

## 🎯 Learning Outcomes

Through this project, I gained practical experience in:

* Java web application development
* Java Servlets and JSP
* JDBC and MySQL database connectivity
* REST/API-based data integration
* Authentication and session management
* CRUD operations
* Admin module development
* Email-based password reset functionality
* Frontend and backend integration
* Deploying Java web applications on Apache Tomcat
* Working with NetBeans and JDK

---

## 🔮 Future Improvements

Potential improvements include:

* Responsive UI improvements
* More detailed match statistics
* Player statistics
* Team profiles
* Improved API error handling
* Caching frequently requested API data
* Role-based access control
* Improved application security
* Automated testing
* Deployment to a cloud platform

---

## 👨‍💻 Author

**Vickey Kushwah**

Java Backend Developer | Java | Spring Boot | REST APIs | MySQL

* GitHub: [@vickykushwah57](https://github.com/vickykushwah57)
* LinkedIn: [Vickey Kushwah](https://www.linkedin.com/in/vickeykushwah57/)
* Email: [kushwahvicky80@gmail.com](mailto:kushwahvicky80@gmail.com)

---

## ⭐ If You Like This Project

If you find this project useful or interesting, consider giving the repository a ⭐ on GitHub.
