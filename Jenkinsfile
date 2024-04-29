pipeline {
    agent any 
    environment {
        CHANGED_FILES = ''
        COMMIT_MESG = ''
        GPT_RULES = ''
    }

    stages {

        stage('Git Details and Rules') {
            steps {
                script {
                    env.CHANGED_FILES = sh(script: "git diff-tree --no-commit-id --name-only -r HEAD", returnStdout: true).trim()
                    echo "Files changed in the last commit: \n${env.CHANGED_FILES}"
                    env.COMMIT_MESG = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
                    echo "Last commit message: \n${env.COMMIT_MESG}"
                    env.GPT_RULES = sh(script: "cat rulesInfo.txt", returnStdout: true).trim()
                    echo "Rules Info: \n${env.GPT_RULES}"
                }
            }
        }

        /*stage('Compile') {
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
                withCredentials([usernamePassword(credentialsId: 'dockerreg', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                sh '''mvn -Ddocker.username="$USERNAME" -Ddocker.password="$PASSWORD" docker:push
                '''
                }
            }
        }*/
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
