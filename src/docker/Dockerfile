FROM openjdk:latest
WORKDIR /src/measurer
COPY MeasurerClient.java /src/measurer/MeasurerClient.java
EXPOSE 8101
RUN javac MeasurerClient.java
CMD ["java", "MeasurerClient"]

