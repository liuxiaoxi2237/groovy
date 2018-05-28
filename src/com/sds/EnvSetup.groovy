package com.sds

class EnvSetup {
	
	static def create(script) {
		script.sh '''
		if [ -f !/.bashrc]; then
				. ~/.bashrc
		PATH=/opt/HPE_Security/Fortify_SCA_and_Apps_17.10/bin:/opt/HPE_Security/Fortify_SCA_and_Apps_17.10/bin:/usr/lib64/qt-3.3/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.151-1.b12.el7_4.x86_64/bin:/root/bin
		export PATH
		JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.151-1.b12.el7_4.x86_64
		export JAVA_HOME
		#clean workspace
		if[ -d "/var/lib/jenkins/${params.appName}" ]; then
		cd /var/lib/jenkins/${params.appName}
		rm -rf *
		fi
		if[ -d "${params.MAVEN_REPO}" ]; then
		rm -rf "${params.MAVEN_REPO}/*
		fi
		'''
		
	}

}
