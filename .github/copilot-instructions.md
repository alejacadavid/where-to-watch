# Project Standards

## Coding Style
- **Backend (Java/Spring Boot):**
  - Use Java 17 features.
  - Prefer `RestClient` over `RestTemplate` or `WebClient` for external HTTP calls.
  - Follow standard Spring Boot structure (Controllers, Services).
  - Use JUnit 5 and Mockito for testing.
- **Frontend (React/Vite):**
  - Use Functional Components and React Hooks (`useState`, `useEffect`).
  - Manage state and API calls logically, ensuring `loading` states are handled.
  - Assume the backend runs on `http://localhost:8080`.

## Security
- **API Keys & Secrets:** Never hardcode API keys or secrets directly in the source code (e.g., `application.properties` or `.jsx` files). Always use environment variables (like `TMDB_API_KEY`) and ensure files containing secrets (like `.env`) are ignored in `.gitignore`.

## Domain Terms
- **TMDB:** The Movie Database API used for fetching movie details and providers.
- **Provider:** A streaming platform service (e.g., Netflix, Max, Disney+).
- **Flatrate:** A streaming subscription model (distinct from renting or buying).
- **CO:** The ISO 3166-1 alpha-2 country code for Colombia, used to filter providers relevant specifically to Colombian users.

## Legal & Attribution
- **TMDB Attribution:** UI and documentation generated must conform to TMDB's attribution guidelines (https://www.themoviedb.org/about/logos-attribution). Any global footers or "About" sections built should display the standard text: *"This product uses the TMDB API but is not endorsed or certified by TMDB."*
