FROM gradle:7.1.1-jdk16-hotspot AS build
COPY --chown=gradle:gradle . /home/eteacher_service
WORKDIR /home/eteacher_service
# RUN gradle build --no-daemon
WORKDIR /home/eteacher_service/build/libs
ENTRYPOINT ["java", "-jar", "eteacher-service.jar"]
