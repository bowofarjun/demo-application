import groovy.json.JsonOutput
import groovy.json.JsonSlurper

pipeline {
    agent any 
    environment {
        CHANGED_FILES = ''
        COMMIT_MESG = ''
        GPT_RULES = ''
        FINAL_GPT_PROMPT = ''
        OPEN_API_KEY = credentials('demo-jenkins-api-key')
        GEMINI_API_KEY = credentials('gemini-api-key')
        APP_ONLY = 'false'
        CONFIG_ONLY = 'false'
        DOCKER_BUILD_AND_PUSH_CONTAINER = 'false'
        DONT_BUILD = 'false'
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
                    /*def apiUrl = 'https://api.openai.com/v1/chat/completions'

                    def data = [
                                model: "gpt-3.5-turbo",
                                messages: [
                                    [role: "user", content: FINAL_GPT_PROMPT]
                               ]
                        ]

                    def requestBody = JsonOutput.toJson(data)
                    requestBody = JsonOutput.prettyPrint(requestBody)
                    echo "Generated JSON: $requestBody"

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

                    def jsonSlurper = new JsonSlurper()
                    def responseData = jsonSlurper.parseText(response.content)
                    */
                    def apiUrl = 'https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent'
                    def data = [
                        contents: [
                            [
                                role: "user",
                                parts: [
                                    [
                                        text: FINAL_GPT_PROMPT
                                    ]
                                ]
                            ]
                        ]
                    ]

                    def requestBody = JsonOutput.toJson(data)
                    requestBody = JsonOutput.prettyPrint(requestBody)
                    echo "Generated JSON: $requestBody"

                    // Make HTTP POST request
                    def response = httpRequest(
                        acceptType: 'APPLICATION_JSON',
                        contentType: 'APPLICATION_JSON',
                        httpMode: 'POST',
                        requestBody: requestBody,
                        url: apiUrl,
                        customHeaders: [
                            [name: 'x-goog-api-key', value: "${env.GEMINI_API_KEY}"],
                            [name: 'Content-Type', value: 'application/json']
                        ]
                    )

                    // Print response to console
                    echo "Response Status: ${response.status}"
                    echo "Response Body: ${response.content}"

                    def jsonSlurper = new JsonSlurper()
                    def responseData = jsonSlurper.parseText(response.content)

                    def text = responseData.candidates[0].content.parts[0].text.replaceAll("```json|```", "").trim()

                    echo "${text}"
                    def finalResponseData = jsonSlurper.parseText(text)

                    APP_ONLY = ${finalResponseData.appOnly}
                    CONFIG_ONLY = ${finalResponseData.configOnly}
                    DOCKER_BUILD_AND_PUSH_CONTAINER = ${finalResponseData.dockerBuildAndPushContainer}
                    DONT_BUILD = ${finalResponseData.dontBuild}

                    echo "${APP_ONLY}"
                    echo "${CONFIG_ONLY}"
                    echo "${DOCKER_BUILD_AND_PUSH_CONTAINER}"
                    echo "${DONT_BUILD}"
                }
            }
        }

        stage('Compile') {
            steps {
                script{
                    if(DONT_BUILD.toBoolean() && APP_ONLY.toBoolean()) {
                    sh '''mvn clean compile
                    '''
                    }
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    if(DONT_BUILD.toBoolean() && (APP_ONLY.toBoolean() || CONFIG_ONLY.toBoolean())) {
                    sh '''mvn clean package
                    '''
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    if(DONT_BUILD.toBoolean() && DOCKER_BUILD_AND_PUSH_CONTAINER.toBoolean()) {
                    sh '''mvn docker:build
                    '''
                    }
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    if(DONT_BUILD.toBoolean() && DOCKER_BUILD_AND_PUSH_CONTAINER.toBoolean()) {
                        withCredentials([usernamePassword(credentialsId: 'dockerreg', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh '''mvn -Ddocker.username="$USERNAME" -Ddocker.password="$PASSWORD" docker:push
                        '''
                        }
                    }
                }
            }
        }
    }
}
