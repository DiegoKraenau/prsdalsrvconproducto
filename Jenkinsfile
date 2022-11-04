pipeline {
    environment {
        KUBECONFIG = '/var/jenkins_home/.kube/aws.config'
        nexusRegistry = 'https://nexus.grupogloria.corp:8443/'
        registryCredential = 'jenkins-nexus'
    }
    agent any
    tools {
        maven 'maven-3.6.3'
        jdk 'openjdk-correto-11'
    }
    
    stages {
        stage('Cloning Git') {
            steps {
                git([url: 'git@gitlab.grupogloria.corp:tcs-dev/visit-service.git', branch: 'master', credentialsId: 'git-ssh-key'])
            }
        }
        
        stage('Compile Stage') {
            steps {
                sh 'mvn clean install'
            }
        }
        
        stage('Build image') {
            steps {
                script {
                    dockerImage = docker.build('visit-service')
                }
            }
        }
        
        stage('Push Docker Images to Nexus Registry') {
            steps {
                script {
                    docker.withRegistry( nexusRegistry, registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
     
        stage ('Deployment Kubernetes') {
            steps {
                script {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'poc-aws-credentials']]){
                        sh "ansible-playbook ${WORKSPACE}/playbook.yaml"
                    }
                }
            }
        }
    }
}