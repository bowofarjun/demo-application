#!/bin/sh

cd /demo-config
tar -xvf *.tar
rm -rf *.tar
cd ..
cp -r /demo-config /tmp_volume

# Infinite loop
while [ "$RESTART_ON_FAILURE" = "true" ]
do
  echo "This script will run forever, until manually stopped."
  sleep 1  # Delays the loop by 1 second to prevent system overload
done