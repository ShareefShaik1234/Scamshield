# ScamShield – AI-Powered Scam Detection System

## About the Project

ScamShield is an AI-powered web application designed to help users identify online scams and fraudulent activities.

The system analyzes URLs, emails, SMS messages, and job-related content to detect potential scams. It uses Google Gemini AI to analyze submitted content and provides a risk score, status, and explanation to help users understand possible threats.

The application also provides scan history, community reports, analytics, and an admin module.

## Technologies Used

### Frontend
- React.js
- Vite
- JavaScript
- HTML5
- CSS3
- Axios

### Backend
- Java
- Spring Boot
- Spring Data JPA
- REST APIs
- MySQL
- Google Gemini AI
- VirusTotalAPI
- Maven

## Main Features

- User Registration and Login
- User Dashboard
- URL Scam Detection
- Email Scam Detection
- SMS Scam Detection
- Job Scam Detection
- AI-Based Scam Analysis
- Risk Score and Detection Explanation
- Scan History
- Community Reports
- Analytics Dashboard
- Admin Module

## System Architecture

The application follows a client-server architecture.

1. **Frontend:** React.js provides the user interface.
2. **Backend:** Spring Boot handles API requests and application logic.
3. **AI Integration:** Google Gemini AI analyzes submitted content and identifies potential scam indicators.
4. **Database:** MySQL stores user information, scan results, and other application data.

## Project Structure

```text
ScamShield/
│
├── frontend/
│   ├── public/
│   ├── src/
│   ├── index.html
│   ├── package.json
│   ├── package-lock.json
│   ├── vite.config.js
│   └── README.md
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   └── README.md
│
└── README.md
```

*The structure above is a general representation. Actual folders may vary.*

## Installation and Setup

### Prerequisites

- Java 21
- Node.js and npm
- MySQL
- Maven
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/ShareefShaik1234/Scamshield
cd ScamShield
```

### 2. Backend Setup

Navigate to the backend folder:

```bash
cd backend
```

Configure your MySQL database and Gemini API key in your local application configuration.

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

Backend runs at:

```text
http://localhost:8080
```

### 3. Frontend Setup

Open another terminal and navigate to the frontend folder:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the frontend:

```bash
npm run dev
```

Frontend runs at:

```text
http://localhost:5173
```

## API Integration

The React frontend communicates with the Spring Boot backend using Axios and REST APIs.

The backend handles:
- User authentication
- URL scanning
- Email scanning
- SMS scanning
- Scan history
- Community reports
- Analytics

The Gemini AI integration is used for AI-based content analysis.

## Security

- API keys and database credentials should be stored in environment variables or local configuration.
- Secret configuration files must not be uploaded to GitHub.
- Input validation is used to handle user-submitted content.

## Future Enhancements

- Improved scam detection accuracy
- Additional scam detection categories
- Enhanced reporting features
- Better analytics and visualizations
- Improved security and performance

## Conclusion

ScamShield aims to provide a simple and accessible platform for identifying potential online scams using AI technology. It helps users recognize suspicious content and make safer decisions online.

