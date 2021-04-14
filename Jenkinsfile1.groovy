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
        
       /* stage('Docker build'){
            steps{
               
                    sh 'docker image prune -a --force'
                    sh 'docker-compose build'
                
                
            }
        }
        stage('Pushing images to docker hub'){
            steps{
                

                withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPWD')]) {
                            // some block
                   sh "docker login -u sujjad -p ${dockerHubPWD}"

                }
                sh "docker commit cisample_app_1 sujjad/todo-app:v${env.BUILD_ID}"
                sh "docker push sujjad/todo-app:v${env.BUILD_ID}"

            }
        }
        stage('deploying it to kubernetes'){
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

