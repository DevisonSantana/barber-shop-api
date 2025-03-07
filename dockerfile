FROM ubuntu as build

RUN apt update && apt install -y openjdk-21-jdk

COPY . .

RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre-alpine

EXPOSE 8080

COPY --from=build /build/libs/shop-V1.0.jar barber-shop-V1.0.jar

ENTRYPOINT [ "java", "-jar", "barber-shop-V1.0.jar" ]
