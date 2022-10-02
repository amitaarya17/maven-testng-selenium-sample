#!/usr/bin/env bash

# This script will start an xvfb screen and then launch the application into it, using the command passed in as arguments.
echo "Starting up xvfb server to take screenshots!"
XAUTHORITY=/tmp/Xauthority Xvfb ":99" -screen 0 1450x1450x16 &
export DISPLAY=:99

echo "Starting test run!"
eval $@