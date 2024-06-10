pipeline {
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Build Gradle') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Sultantokan/application']])
                bat 'gradle clean build'
            }
        }
        stage('Build docker image') {
            steps {
                script {
                    bat 'docker build -t sultantokan/application .'
                }
            }
        }
        stage('push docker') {
            steps {
                bat 'docker login -u sultantokan -p Sultan777Lol'
                bat 'docker push sultantokan/application'
            }
        }
    }
}
