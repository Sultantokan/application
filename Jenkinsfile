pipeline {
    agent any
    tools{
        gradle 'gradle'
    }
    stages{
        stage('Build Gradle'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Sultantokan/application']])
                sh 'gradle clean build'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t sultantokan/application .'
                }
            }
        }
        stage('push docker') {
            steps {
                sh 'docker login -u sultantokan -p Sultan777Lol'
                sh 'docker push sultantokan/application'
            }
        }
        stage('deploy k8s') {
            steps {
                sh 'kubectl delete deployment spring-boot-app || true'
                sh 'kubectl apply -f k8s/metrics.yaml'
                sh 'kubectl apply -f k8s/postgres.yaml'
                sh 'kubectl apply -f k8s/application.yaml'
            }
        }
    }
}