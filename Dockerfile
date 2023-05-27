FROM openjdk

WORKDIR /app

COPY target/crud-oak-tec-1.0.jar /app/crud-oak-tec.jar

# Define o comando de inicialização da aplicação
CMD ["java", "-jar", "crud-oak-tec.jar"]
