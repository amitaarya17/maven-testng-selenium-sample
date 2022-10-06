# maven-testng-selenium-sample
Sample repository built using maven, testng, selenium, allure reports

## Run using docker
### Run the selenium grid (with one chrome container)
* $ docker network create grid
* $ docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:4.5.0-20220929
* $ docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub \
--shm-size="2g" \
-e SE_EVENT_BUS_PUBLISH_PORT=4442 \
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
selenium/node-chrome:4.5.0-20220929
### Install maven and chromedriver on host machine
### Run the tests on grid node
* $ mvn clean compile test -Dtest.environment=grid -DrunSuite=testng

## Run tests in a standalone chrome docker container
* $ docker build .
* $ docker run --volume <path_to_local_folder>:/usr/bin/app/allure-results <image id> scripts/run_scripts_local.sh local testng


### To generate test reports, run the following commands
* $ cd <folder where allure-results folder exists>
* $ allure serve
* Reports open in the browser
