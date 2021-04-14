FROM maven:3.5-jdk-8
COPY var/jenkins_home/workspace/CI_CD_Pipeline/app/target/TodoDemo-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT [ "java","-jar","app.war" ]