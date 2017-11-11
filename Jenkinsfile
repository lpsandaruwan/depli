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
        parallel(
          "Test": {
            sh 'mvn test'
            
          },
          "SonarAnalysis": {
            sh 'mvn sonar:sonar'
            
          },
          "": {
            archiveArtifacts '*.jar'
            
          }
        )
      }
    }
  }
}