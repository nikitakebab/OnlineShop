# Description
This project is a backend for an online shoe store. It is written on the Spring framework and currently uses the H2 database. I plan to replace it with MySQL in the future.
# Necessary software to launch the project
To run the project, you will need maven and java 20 installed
# Starting project via Maven
You need to execute 2 commands in the terminal:
- mvn install
- mvn exec:java -Dexec.mainClass="com.example.App"

It opens on port 8080 of your local host.
# Using
You can view the API specification at the link localhost:8080/swagger-ui/index.html

You can also send existing GET and POST requests there.