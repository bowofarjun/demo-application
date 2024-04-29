pipeline {
    agent any 
    environment {
        CHANGED_FILES = ''
        COMMIT_MESG = ''
        GPT_RULES = ''
        FINAL_GPT_PROMPT = ''
        OPEN_API_KEY = credentials('demo-jenkins-api-key')
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
                    FINAL_GPT_PROMPT = sh(script: "echo '${GPT_RULES}\n\n\nBelow are the files in git commit (tree order):\n${CHANGED_FILES}\n\n\nAlso git commit message is: ${COMMIT_MESG}'", returnStdout: true)
                    echo "Final Prompt: \n\n${FINAL_GPT_PROMPT}"
                }
            }
        }

        stage('Call ChatGPT and Save Response'){
            steps {
                script {
                    // Define API URL
                    def apiUrl = 'https://api.openai.com/v1/chat/completions'

                    // Define the request body
                    def requestBody = """
                    {
                        "model": "gpt-3.5-turbo",
                        "messages": [
                            {"role": "user", "content": "${FINAL_GPT_PROMPT}"}
                        ]
                    }
                    """

                    // Make HTTP POST request
                    def response = httpRequest(
                        acceptType: 'APPLICATION_JSON',
                        contentType: 'APPLICATION_JSON',
                        httpMode: 'POST',
                        requestBody: requestBody,
                        url: apiUrl,
                        customHeaders: [
                            [name: 'Authorization', value: "Bearer ${env.OPEN_API_KEY}"],
                            [name: 'Content-Type', value: 'application/json']
                        ]
                    )

                    // Print response to console
                    echo "Response Status: ${response.status}"
                    echo "Response Body: ${response.content}"
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
