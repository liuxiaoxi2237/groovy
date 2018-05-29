import static com.sds.fortiScan.*;
def call(body) {
	
		def config = [:]
		body.resolveStrategy = Closure.DELEGATE_FIRST
		body.delegate = config
		body()
	
		pipeline {
			agent {
				node {
					label 'masternode'
				}
			}
			
			tools {
				maven 'MAVEN3.0'
				jdk 'JDK1.8'
				}
		
			   stages {
					stage('initialize') {
						steps {
							sh '''
							PATH = $PATH:/opt/HPE_Security/Fortify_SCA_and_Apps_17.10/bin
							export PATH
							echo $PATH
							pwd
							'''
							git credentialsId: 'd8410c40-131c-457b-b80f-096a23f20c0a', url: 'http://10.62.81.24/binbinw/data-transfer-service.git'
							
							sh '''
							mvn clean
							'''                
						}
					}
		
		stage('build') {
			steps {
				sh '''
                    mvn install
					mkdir app-lib
					find /var/lib/jenkins/workspace/${params.projectName}/${params.appName}  -name *.jar -exec cp {} ./app-lib  \";"
					mkdir maven-lib
					find ${params.maven_repo}  -name *.jar -exec cp {} ./maven-lib \";"
                    '''
			}
		}
		
		stage('scan') {
				steps {
					sh '''
						appName01 = $params.appName
						projectName01 = $params.projectName
					'''
					fscan(this,appName01,projectName01)
				}
		}
					
		}
	}
}
		