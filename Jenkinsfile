pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo "Getting Projet From Git";
                git branch: 'Produit', 
                url: 'https://github.com/ABSnader/Project_CICD.git',
                credentialsId:'gitid';
            }
            
                  
        }
        
        
         stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'
            }
        }
        
       stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
            }
        }

        
        stage('MVN PACKAGE') {
            steps {
                sh "chmod +x mvnw "
                sh "./mvnw package -DskipTests=true"
                
            }
        }
        
        
        stage('MVN TEST') {
            steps {
                sh "mvn test"
                junit '**/target/surefire-reports/TEST-*.xml'
                jacoco execPattern: 'target/jacoco.exec' 
            }
        }
        
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -DskipTests=true -Dsonar.host.url=http://192.168.1.15:9000 -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
        
        stage('NEXUS'){
            steps{
               sh 'mvn deploy -DskipStaging=true -Dmaven.deploy.skip=false -Dmaven.test.skip=true' // sh 'echo NEXUS'  //
            }
        }
        
        stage('Pull Jar From Nexus') {
            steps {
                sh "curl http://192.168.1.15:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar --output achat-1.0.jar";
            }
        }
      
        stage('Build Docker Image') {
            steps {
              
                script {
                    dockerImage = docker.build "192.168.1.15:8083/repository/arij/achat:latest"
                }
            }
        }
        
           stage('Push Image To Nexus') {
            steps {
                script{
                    withDockerRegistry([credentialsId: 'nexus', url: 'http://192.168.1.15:8083/repository/arij/']) {
                        sh "docker push 192.168.1.15:8083/repository/arij/achat:latest"
                    }
                }
            }
        }
        
        stage('Pull Image From Nexus') {
            steps {
                script{
                    withDockerRegistry([credentialsId: 'nexus', url: 'http://192.168.1.15:8083/repository/arij/']) {
                        sh "docker pull 192.168.1.15:8083/repository/arij/achat:latest"
                    }
                }
            }
        }
        
        stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d '
            }
        }

	

    }

	post {
        always {

	emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
        }
	
    }
}