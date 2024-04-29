pipeline {
    agent any  // This specifies that the pipeline can run on any available agent

    stages {
        stage('Checkout') {
            steps {
                // Checks out the source code from a specified repository
                echo 'Checkout from Git'
            }
        }

        stage('Build') {
            steps {
                // Run a build command, could be a script or a tool command
                echo 'Building the project...'
                //sh 'make build' // This is an example, replace with your actual build command
            }
        }

        stage('Test') {
            steps {
                // Run tests on the built project
                echo 'Running tests...'
                //sh 'make test' // This is an example, replace with your actual test command
            }
        }
    }

    post {
        always {
            // This block will always execute, even if the build fails
            echo 'This will always run regardless of the build result.'
        }
        success {
            // Actions to take if the pipeline succeeds
            echo 'Build was successful!'
        }
        failure {
            // Actions to take if the pipeline fails
            echo 'Build failed.'
        }
    }
}
