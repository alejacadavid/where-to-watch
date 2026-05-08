# 🇨🇴 DondeLoVeo (Where to Watch)

A full-stack application to find out where to stream your favorite movies in Colombia. 

This repository contains two main components:
- **Backend**: A Java 17 Spring Boot app that communicates with the TMDB API.
- **Frontend**: A React single-page application powered by Vite.

## � Preview

![App Demo](docs/images/demo.gif)

## �📋 Prerequisites

Make sure you have the following installed on your machine:
- [Node.js](https://nodejs.org/) (v16 or higher)
- [npm](https://www.npmjs.com/)
- [Java 17](https://adoptium.net/) (JDK 17)
- [Gradle](https://gradle.org/) (optional, as there is a Gradle wrapper provided)

---

## 🚀 Getting Started

### 1. Backend Setup

The backend handles API requests to The Movie Database (TMDB).

1. Open a terminal and navigate to the backend directory:
   ```bash
   cd backend
   ```

2. **Configure your TMDB API Key:**
   Copy the example environment file and add your API key:
   ```bash
   cp .env.example .env
   # Edit .env and replace 'your_api_key_here' with your actual TMDB API key
   ```

3. Run the Spring Boot application (passing the environment variable):
   ```bash
   export $(cat .env | xargs) && ./gradlew bootRun
   # or if using a global gradle installation:
   export $(cat .env | xargs) && gradle bootRun
   ```
   > The backend server will start on **http://localhost:8080**.

### 2. Frontend Setup

The frontend provides the user interface to search for movies and view platforms.

1. Open a new terminal window and navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install the necessary dependencies:
   ```bash
   npm install
   ```

3. Start the Vite development server:
   ```bash
   npm run dev
   ```
   > The frontend application will be available at **http://localhost:5173**.

---

## 🧪 Running Tests

### Backend Tests

To run the JUnit test suite for the Spring Boot application:

```bash
cd backend
./gradlew test
# or
gradle test
```

### Frontend Tests

The React frontend uses Vitest and React Testing Library. To run them:

```bash
cd frontend
npm run test
```

## 🛠 Tech Stack

- **Backend:** Java 17, Spring Boot 3.4.x, Spring Web, RestClient, JUnit 5, Mockito.
- **Frontend:** React 18, Vite, standard CSS.

---

## 📽️ Attribution

This product uses the [TMDB API](https://www.themoviedb.org/) but is not endorsed or certified by TMDB. 

<img src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg" alt="TMDB Logo" width="150" />
