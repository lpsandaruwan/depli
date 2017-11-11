pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install -DskipTests'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('SonarAnalysis') {
      steps {
        sh 'mvn sonar:sonar'
      }
    }
  }
}