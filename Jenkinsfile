pipeline {
  agent any
  stages {
    stage('Initialize') {
      steps {
        echo 'Initializing'
        bat 'mvn clean'
      }
    }

    stage('Build') {
      steps {
        bat 'mvn package -Dmaven.test.skip=true'
      }
    }

    stage('Static Analysis') {
      steps {
        bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
      }
    }

    stage('Push to Hub') {
      steps {
        bat 'docker build -t pixels-gateway .'
        bat 'docker tag pixels-gateway abhi2104/pixels-gateway:latest'
        bat 'docker push abhi2104/pixels-gateway:latest'
      }
    }
    
    stage('Deploy') {
      agent {
        node {
          label 'jenkinsagent'
        }

      }
      options { skipDefaultCheckout() }
      steps {
        sh 'docker pull abhi2104/pixels-gateway:latest'
      }
    }

  }
}