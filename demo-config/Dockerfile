FROM openjdk:18-alpine

RUN mkdir /demo-config

COPY ./target/demo-config*.tar /demo-config/
COPY ./start.sh /
RUN chmod +x /start.sh
ENTRYPOINT ["/start.sh"]
