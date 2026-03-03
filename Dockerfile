# ─────────────────────────────────────────────
# Stage 1: BUILD
# ─────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Önce sadece pom.xml kopyala → dependency cache katmanı
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Kaynak kodları kopyala ve paketle
COPY src ./src
RUN mvn package -DskipTests -q

# ─────────────────────────────────────────────
# Stage 2: RUN
# ─────────────────────────────────────────────
FROM eclipse-temurin:17-jre AS run

WORKDIR /app

COPY --from=build /app/target/shopease-patterns.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
