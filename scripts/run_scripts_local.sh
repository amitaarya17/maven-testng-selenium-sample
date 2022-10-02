#!/usr/bin/env bash
# ENV is the environment where the test will be run i.e. local or grid
# TEST_SUITE is the testng xml file which will drive the tests to be run

ENV=$1
TEST_SUITE=$2

echo "Executing ${TEST_SUITE} on ${ENV}"
mvn -Dmaven.repo.local=/cache/maven.repository clean test -fae \
        -Dtest.environment=${ENV} \
        -DrunSuite=${TEST_SUITE}