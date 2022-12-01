# Concesionaria API
- Version 1.0
- Authors: [Leonel Zeballos](https://github.com/LeooZeballos) & [Tomas Muzzillo](https://github.com/tmuzzillo)
- Creation date: 05-09-2022
- Made for "Programación Avanzada" subject at UTN FRVM

## How to run the backend

### Backend-only

1. Make sure Java 8 or higher is installed.
2. Build the project with maven to have all dependencies.
3. Connect to a postgres database and set the connection values in the application properties.
4. Run the application.

### Docker

1. Make sure docker and docker-compose is installed.
2. Download the project or the docker-compose.yml file in the root.
3. Open a terminal, and go to the project folder, or wherever docker-compose.yml is running.
4. Use `docker-compose up -d` to start the application, plus the backend and postgres database + pgadmin4. The frontend will run in port 3000, while the backend will run in port 8080. This creates a /db folder where you started the command, to hold the database.

Note: To stop and delete the four containers, use `docker-compose down`.

## Extra
The application has a SwaggerUI to test the endpoints. The main root redirects to the swagger ui.
