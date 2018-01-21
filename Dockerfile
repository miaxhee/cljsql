FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/cljsql.jar /cljsql/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/cljsql/app.jar"]
