node {

	stage( 'checkout' ) {
		checkout(
				[$class                           : 'GitSCM', branches: [[name: '*/master']],
				 doGenerateSubmoduleConfigurations: false,
				 extensions                       : [], submoduleCfg: [],
				 userRemoteConfigs                : [[credentialsId: 'test', url: 'https://github.com/idrissov/duck-maze.git']]
				]
		)
	}
	stage( 'build' ) {
		sh 'mvn clean install'
	}

}
