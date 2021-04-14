pipeline{
    agent any;
     tools{
       maven 'maven'
       jdk 'JDK11'
   }
    stages{
        stage('Fetch project from github'){
            steps{
                git branch: 'master', url: 'https://github.com/Harvey2504/todo-app.git'
            }
                
        }
        stage('Maven package'){
            steps{
                sh 'mvn -f app/pom.xml package'

            }
        }
        
        stage("docker build"){
            steps{
                sh "docker-compose up -d"
            }
        }
        stage("commiting the docker images"){
            steps{
                sh "docker commit todo-app harvey2504/todo-app:v${env.BUILD_ID}"
                sh "docker commit todo-mysql harvey2504/todo-mysql:v${env.BUILD_ID}" 
            }
        }
        stage("pushing the images to docker hub"){
            steps{
                withDockerRegistry([ credentialsId: "docker-token", url: "" ]){
                    sh "docker push harvey2504/todo-app:v${env.BUILD_ID}"
                    sh "docker push harvey2504/todo-mysql:v${env.BUILD_ID}"
                }
            }
        }


      
       /* stage('deploying it to kubernetes'){
            steps{
                sh 'chmod +x change-tag.sh'
                sh """./change-tag.sh v${env.BUILD_ID}"""
                sh 'cat k8s/api-deployment.yaml'
                withKubeConfig(caCertificate: '', clusterName: '', contextName: '', credentialsId: 'kube-azure', namespace: '', serverUrl: 'https://content-jackal-k8s-656e8d92.hcp.westus2.azmk8s.io:443') {
                                // some block
                    sh 'kubectl apply -f k8s/database-deployment.yaml'
                    
                }
                sleep(120)
                withKubeConfig(caCertificate: '', clusterName: '', contextName: '', credentialsId: 'kube-azure', namespace: '', serverUrl: 'https://content-jackal-k8s-656e8d92.hcp.westus2.azmk8s.io:443') {
                                // some block
                    sh 'kubectl apply -f k8s/api-deployment.yaml'
                    sh 'kubectl get pods'
                    sh 'kubectl get svc'
                    
                }

            }
        } */
    

    }
}

