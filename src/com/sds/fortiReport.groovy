package com.sds

class fortiReport {
	
	static def fscan(script,appName) {
		
		script.sh '''
			pwd
			sh /var/lib/jenkins/scripts/Fortify$appName.sh
			ReportGenerator -format PDF -source Fortifymarvin_${appName}.fpr -user ${userName} -f Fortifymarvin_${appName}_Scan.pdf 
			subject="${appName} module Fortify scan report"
			recipients="David.G.Liu@emc.com xi.yang@emc.com"
			mkdir -p ~/tmp/fortify_reports
			mv -f Fortifymarvin_commons_Scan.pdf ~/tmp/fortify_reports
			mutt -s "$subject" -a ~/tmp/fortify_reports/Fortifymarvin_commons_Scan.pdf -- $recipients < /var/lib/jenkins/mail_templates/common
			rm -rf ~/tmp
			'''
	}

}
