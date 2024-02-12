FROM maven:3.8.5-openjdk-11
WORKDIR app
COPY . .
RUN mvn clean install
EXPOSE 8080
CMD mvn spring-boot:run
