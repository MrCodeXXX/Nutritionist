FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=food
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/food
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
EXPOSE 9021
ENTRYPOINT ["java","-jar","userservice-1.0.jar"]
