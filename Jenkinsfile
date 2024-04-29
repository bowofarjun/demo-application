pipeline {
    agent any 

    stages {
        stage('Compile') {
            steps {
                
                sh '''mvn clean compile
                '''
            }
        }

        stage('Package') {
            steps {
                
                sh '''mvn clean package
                '''
                
            }
        }

        stage('Docker Build') {
            steps {
                sh '''mvn docker:build
                '''
            }
        }

        stage('Docker Push') {
            steps {
                sh '''mvn -Ddocker.username="2022mt12119" -Ddocker.password="@rjun93M!" docker:push
                '''
            }
        }
    }

    /*post {
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
    }*/
}
