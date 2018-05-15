FROM java:8
ADD /target/*.jar categorie-service.jar
ENTRYPOINT ["java","-jar","categorie-service.jar"]