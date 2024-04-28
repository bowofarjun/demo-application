#!/bin/sh

java -jar -Dspring.thymeleaf.prefix=file:/tmp_volume/demo-config/templates/ -Dspring.config.location=/tmp_volume/demo-config/application.properties /demo-app/demo-app*.jar
# Infinite loop
while [ "$RESTART_ON_FAILURE" = "true" ]
do
  echo "This script will run forever, until manually stopped."
  sleep 1  # Delays the loop by 1 second to prevent system overload
done