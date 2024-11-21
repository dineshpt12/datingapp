How to run the project:
Pre-requisite:
Java 21:

Download the project source folder datingsuggestions from below Github repository
https://github.com/dineshpt12/datingapp.git

Extract the folder in C:\Working\Projects\datingsuggestions
Make sure JDK 21 is installed on the machine if not download JDK 21 from the below link:
https://www.oracle.com/in/java/technologies/downloads/#jdk21-windows
Extract the zip jdk-21.0.5 and copy the fonder in C drive:
Go to command prompt and go to project folder with command:

cd C:\Working\Projects\datingsuggestions
C:\Working\Projects\datingsuggestions>set JAVA_HOME=C:\MyPrograms\jdk-21.0.5
C:\Working\Projects\datingsuggestions>set PATH=%PATH%;%JAVA_HOME%\bin
C:\Working\Projects\datingsuggestions>java -version
java version "21.0.5" 2024-10-15 LTS
Java(TM) SE Runtime Environment (build 21.0.5+9-LTS-239)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.5+9-LTS-239, mixed mode, sharing)

Type below command to run the project:
mvnw spring-boot:run

Once server is up, open the browser and run below URL:
http://localhost:8080/datingapp/2/topmatches/2

H2 Database Console:
http://localhost:8080/h2-console
