pipeline {
    agent any
    
    stages {
        stage('Pull GIT') {
            steps {
                echo 'Pulling...';
                  git branch: 'Operateur',
                  url : 'https://github.com/ABSnader/Project_CICD.git',
                  credentialsId:'gitID';
                  
            }
        }
        
        stage('Maven Package') {
            steps {
                sh "chmod +x mvnw "
                sh "./mvnw package -DskipTests=true"
                
            }
        }
        
        stage('Junit Mockito Jacoco') {
            steps {
                sh "./mvnw test"
                junit '**/target/surefire-reports/TEST-*.xml'
            jacoco execPattern: 'target/jacoco.exec' 
            }
        }
        
        stage('SonarQube') {
            steps {
                sh "./mvnw sonar:sonar -DskipTests=true -Dsonar.host.url=http://192.168.56.10:9000 -Dsonar.login=admin -Dsonar.password=azerty123"
            }
        }
        
        stage('Nexus3'){
            steps{
                nexusArtifactUploader artifacts: [
                    [
                        artifactId: 'achat', 
                        classifier: '', 
                        file: 'pom.xml', 
                        type: 'pom'
                    ]
                ], 
                credentialsId: 'nexus', 
                groupId: 'tn.esprit.rh', 
                nexusUrl: '192.168.56.10:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: 'maven-releases', 
                version: '1.0'
                
            }
        }
        
        stage('Build Image') {
            steps{
                sh 'docker build -t salmabha/salma_dev:latest .'
            }
        }
        
		stage('Docker Login'){
            steps{
                sh 'docker login -u salmabha -p azerty123'
            }
        }
        
		/*stage('Push Image'){
            steps{
                sh 'docker push salmabha/salma_dev:latest'
            }
        }*/
        
		stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d '
            }
        }
        
        post {
            failure {
                mail to: "salma.belhadjali@esprit.tn",
                subject: "Build Fail",
                body: "Fail"
            }
        }
        
    }
}
