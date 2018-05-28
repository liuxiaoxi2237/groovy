package com.sds

class appBuild {
	
	static def buildapp(script) {
		
		script.sh '''
			mkdir -p /var/lib/jenkins/workspace/${params.appName}
			cd /var/lib/jenkins/workspace/${params.appName}
            git branch: "${params.branchName}", credentialsId: '90807c3d-87c4-4791-aed6-dbb012d92c7b', url: "git@10.62.81.24:incubation/railai.git"
			cd ./${params.appName}
			mvn clean
			mvn install
			#mkdir -p /var/lib/jenkins/workspace/${params.appName}/cppath
			#find /var/lib/jenkins/workspace/${params.appName}  -name *.jar -exec cp {} /var/lib/jenkins/workspace/${params.appName}/cppath  
			find ${params.MAVEN_REPO}  -name *.jar -exec cp {} /var/lib/jenkins/workspace/${params.appName}/cppath 
			'''
	}

}
