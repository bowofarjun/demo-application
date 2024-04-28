FROM openjdk:18-alpine

RUN mkdir /demo-app

COPY ./target/demo-app*.jar /demo-app/
COPY ./start.sh /
RUN chmod +x /start.sh
ENTRYPOINT ["/start.sh"]
