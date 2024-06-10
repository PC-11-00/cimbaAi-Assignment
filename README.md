# Summariser React Frontend

This app will have a React frontend which will have two pages. One of the pages will take a website URL as input and, on submitting it, will summarize the website. The functionalities will be the same as if you ask ChatGPT “Summarize http://cimba.ai”.

The second page will have the history of previous requests.

## Architecture

- **User:** Interacts with the React app.
- **React App:** Serves the frontend interface.
- **Backend - Java Spring Boot Service:** Handles the API requests and calls the Scala library for operations.
- **Scala Library:** Performs core operations and interacts with the Postgres database.
- **Postgres DB:** Stores the history of all requests.
- **Python FastAPI Service:** Communicates with OpenAI or other LLM endpoints to summarize content.
- **OpenAI:** Provides the summarization service.

## Approach

### Create Three Services

1. **Backend - Java Spring Boot**
   - Use Gradle for building.
   - A Spring Boot API server.
   - Calls the Scala library for all operations.

2. **Scala**
   - Use Gradle for building.
   - Functions as a library, called from the Java Spring Boot server.
   - Connected to the Postgres DB to log all request history.

3. **Python**
   - Use FastAPI.
   - Calls OpenAI or any other LLM endpoint to summarize contents and return them to the web.

### Create a React Website
- A simple form application.
- History page with all previous requests.

### User Interaction
- The user submits a LinkedIn URL or any website URL.
- The backend crawls the website and summarizes it using OpenAI or any LLM endpoint.

## Running the Project

### Prerequisites
- Postgres database running.
- Node.js and npm installed.
- Java and Gradle installed.
- Python and FastAPI installed.

### Steps

1. **Clone the repository:**
    ```sh
    git clone https://github.com/your-repo/summariser-react-frontend.git
    cd summariser-react-frontend
    ```

2. **Install frontend dependencies:**
    ```sh
    cd frontend
    npm install
    ```

3. **Start the React app:**
    ```sh
    npm start
    ```

4. **Setup the backend services:**
   - **Java Spring Boot:**
     ```sh
     cd backend
     ./gradlew bootRun
     ```
   - **Scala Library:**
     - Ensure it's built and available for the Java Spring Boot to call.

5. **Start the Python FastAPI service:**
    ```sh
    cd python-service
    uvicorn main:app --reload
    ```

6. **Ensure Postgres is running:**
   - Setup the database connection parameters in your services.
