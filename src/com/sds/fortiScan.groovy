package com.sds

class fortiScan {
	
	static def fscan(script,appName,projectName) {
		
		script.sh '''
			pwd
			sh /var/lib/jenkins/scripts/Fortify_$appName.sh $appName $projectName
			'''
	}
}
