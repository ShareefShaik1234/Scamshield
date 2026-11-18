# ScamShield – Backend

## About the Project

ScamShield is an AI-powered scam detection system developed to identify potential online scams and fraudulent activities.

The backend is developed using Java and Spring Boot. It provides REST APIs to communicate with the React frontend, processes user requests, integrates Google Gemini AI for scam analysis, and stores application data in a MySQL database.

It analyzes URLs, emails, SMS messages, and job-related content and provides a risk score, status, and explanation.

## Technologies Used

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Google Gemini AI API
- RestTemplate
- Jackson (ObjectMapper and JsonNode)

## Features

- User Registration and Login
- URL Scam Detection
- Email Scam Detection
- SMS Scam Detection
- Job Scam Detection
- AI-Based Content Analysis
- Risk Score Generation
- Scam Status and Explanation
- Scan History Management
- Community Reports
- Analytics Support
- Admin Functionality

## Project Architecture

The backend follows a layered architecture:

1. **Controller Layer:** Handles incoming HTTP requests and returns API responses.
2. **Service Layer:** Contains application logic and processes scam detection requests.
3. **Repository Layer:** Communicates with the MySQL database using Spring Data JPA.
4. **Entity Layer:** Represents database entities.
5. **DTO Layer:** Transfers data between the frontend and backend.
6. **Gemini Integration:** Communicates with Google Gemini AI to analyze submitted content.

## AI Integration – Google Gemini

ScamShield uses Google Gemini AI to analyze submitted content.

The GeminiService:
- Sends submitted content to the Gemini API.
- Receives the AI-generated response.
- Extracts the generated JSON text.
- Parses the response using Jackson.
- Maps the result into a ScanResponse object.

The AI analysis provides:

- `status` – Classification of the submitted content.
- `riskScore` – Risk score indicating the potential threat.
- `reason` – Explanation of the analysis.

Example response:

```json
{
  "status": "MALICIOUS",
  "riskScore": 90,
  "reason": "The message contains suspicious links and urgent requests."
}
```

## API Endpoints

### Message Scanner

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/scan/email` | Analyze email content |
| POST | `/scan/sms` | Analyze SMS content |
| GET | `/scan/messages` | Retrieve message scan history |
| DELETE | `/scan/messages/{id}` | Delete a message scan |

### Request Example

For email and SMS scanning:

```json
{
  "content": "Your account has been suspended. Click here immediately."
}
```

The backend analyzes the submitted content and returns the scan result.

*Other API endpoints are available depending on the implemented controller modules.*

## Database

MySQL is used to store application data.

Spring Data JPA and Hibernate are used for database operations.

The database supports:
- User information
- Scan records
- Message scan history
- Community reports
- Other application data

## Installation and Setup

### Prerequisites

- Java 21
- Maven
- MySQL Server
- Git
- Google Gemini API key

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### 2. Navigate to Backend

```bash
cd ScamShield/backend
```

### 3. Configure MySQL

Create your MySQL database and configure the database connection in your local application configuration.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scamshield
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

### 4. Configure Gemini API Key

Set your Gemini API key through an environment variable or secure local configuration.

```properties
gemini.api.key=${GEMINI_API_KEY}
```

Do not upload actual passwords or API keys to GitHub.

### 5. Run the Backend

Using Maven:

```bash
mvn spring-boot:run
```

The backend runs at:

```text
http://localhost:8080
```

## Testing

Postman can be used to test the backend REST APIs.

Example:

```text
POST http://localhost:8080/scan/email
```

Request body:

```json
{
  "content": "Dear customer, your account has been suspended. Click here immediately."
}
```

The backend returns the analysis result, including the status, risk score, and reason.

## Frontend Integration

The backend communicates with the React frontend through REST APIs.

The frontend uses Axios to send HTTP requests to the Spring Boot backend.

**Frontend local URL:**

```text
http://localhost:5173
```

**Backend local URL:**

```text
http://localhost:8080
```

## Security

- API keys are stored securely.
- Database credentials should not be exposed.
- Input validation is used for incoming requests.
- Sensitive information should not be included in application logs.
- Secret configuration files must not be committed to GitHub.

## Conclusion

The ScamShield backend provides the core functionality of the scam detection application. By combining Spring Boot, MySQL, REST APIs, and Google Gemini AI, it enables intelligent scam analysis and communication with the frontend.


