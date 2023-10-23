#!groovy

javaBuild{
	module="enedis-grafana-dsl"
	devMails="dsi-nexus-weco-chutney@g.enedis.fr"
	userEmail="jenkins-commun-hors-linky@enedis.fr"
	userName="Jenkins Commun hors Linky"
	releaseBranch="main"
	jdk="openjdk-enedis-17.0.3_7"
	mavenBuildGoal="clean install -U"
	mavenDeployGoal="clean deploy -DskipTests"
}