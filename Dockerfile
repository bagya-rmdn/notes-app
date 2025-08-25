# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app

# copy maven wrapper + config
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# download dependencies (cache layer)
RUN ./mvnw dependency:go-offline -B

# copy source
COPY src src

# build app (skip tests untuk mempercepat)
RUN ./mvnw clean package -DskipTests

# ---------- Run Stage ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# copy jar dari build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
