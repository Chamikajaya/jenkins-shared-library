#!/usr/bin/env groovy

def call() {
    echo "Building jar artifact"
    sh "mvn clean package"
}