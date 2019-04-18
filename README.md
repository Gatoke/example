# Example of Spring Boot project
This is the example of Spring Boot project. 
The project is written in hexagonal architecture.

To clone this repository use `git clone https://github.com/Gatoke/example.git`

Documentation is available on _/swagger-ui.html_

If you want to run this application on docker, go to root directory and run:
1. `./gradlew clean build`
2. `docker build -t gatoke/example:1 .`
3. Go to /deployment directory and run: `docker-compose up -d`
4. Check on http://localhost:8080/swagger-ui.html
