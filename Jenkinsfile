pipeline {
    agent any 
    environment {
        CHANGED_FILES = ''
        COMMIT_MESG = ''
        GPT_RULES = ''
        FINAL_GPT_PROMPT = ''
    }

    stages {

        stage('Git Details and Rules') {
            steps {
                script {
                    CHANGED_FILES = sh(script: "git diff-tree --no-commit-id --name-only -r HEAD", returnStdout: true).trim()
                    echo "Files changed in the last commit: \n${CHANGED_FILES}"
                    COMMIT_MESG = sh(script: "git log -1 --pretty=%B", returnStdout: true).trim()
                    echo "Last commit message: \n${COMMIT_MESG}"
                    GPT_RULES = sh(script: "cat rulesInfo.txt", returnStdout: true).trim()
                    echo "Rules Info: \n${GPT_RULES}"
                }
            }
        }

        stage('Create prompt'){
            steps {
                script {
                    FINAL_GPT_PROMPT = sh(script: "${GPT_RULES}\n\n\nBelow are the files in git commit (tree order):\n${CHANGED_FILES}\n\n\nAlso git commit message is: ${COMMIT_MESG}", returnStdout: true)
                    echo "Final Prompt: \n\n${FINAL_GPT_PROMPT}"
                }
            }
        }

        stage('Call ChatGPT and Save Response'){
            steps {
                echo "Hello World"
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