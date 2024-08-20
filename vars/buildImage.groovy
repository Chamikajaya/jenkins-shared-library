#!/usr/bin/env groovy

def call() {
    echo "Building docker image"

    withCredentials([usernamePassword(
            // The ID used to store the credentials in Jenkins
            credentialsId: 'docker-hub-credentials',
            // The variable that will hold the password from the credentials
            passwordVariable: 'DOCKER_HUB_PASSWORD',
            // The variable that will hold the username from the credentials
            usernameVariable: 'DOCKER_HUB_USERNAME'
    )]) {
        // Execute a shell command to build a Docker image
        // TODO: Later increment the tag dynamically -->
        sh "docker build -t chamikajay/jenkins-simple-app:1.0.1 ."

        // Execute a shell command to log in to Docker Hub
        // The `echo $DOCKER_HUB_PASSWORD` part is used to pass the password to the `docker login` command
        // The `-u` option is used to specify the username
        // The `--password-stdin` option tells Docker to read the password from the standard input
        sh "echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin"

        // Execute a shell command to push the Docker image to Docker Hub
        sh "docker push chamikajay/jenkins-simple-app:1.0.0"
    }

}