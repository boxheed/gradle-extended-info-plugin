package com.fizzpod.gradle.plugins.info.ci


import nebula.plugin.info.ci.JenkinsProvider
import nebula.plugin.info.ci.UnknownContinuousIntegrationProvider
import nebula.test.ProjectSpec;

import com.fizzpod.gradle.plugins.info.ci.ContinuousIntegrationInfoProviderResolver

class ContinuousIntegrationInfoProviderResolverSpec extends ProjectSpec {

	def 'get all configured info providers'() {
		when:
		def resolver = new ContinuousIntegrationInfoProviderResolver()

		then:
		def providers = resolver.all()
		providers != null
		providers.size() == 5
		providers[0].getClass().equals(JenkinsProvider.class)
		providers[1].getClass().equals(DroneIoProvider.class)
		providers[2].getClass().equals(ShippableProvider.class)
		providers[3].getClass().equals(WerckerProvider.class)
		providers[4].getClass().equals(UnknownContinuousIntegrationProvider.class)
	}

	def 'get Jenkins provider if running on Jenkins'() {
		when:
		def onJenkins = System.getenv('BUILD_NUMBER') && System.getenv('JOB_NAME')
		def onDrone = System.getenv('DRONE')
		def onShippable = System.getenv('SHIPPABLE')
		def onWercker = System.getenv('WERCKER_ROOT')
		def resolver = new ContinuousIntegrationInfoProviderResolver()

		then:
		def provider = resolver.findProvider(project)
		if(onJenkins) {
			provider.getClass().equals(JenkinsProvider.class)
		} else if(onDrone) {
			provider.getClass().equals(DroneIoProvider.class)
		} else if(onShippable) {
			provider.getClass().equals(ShippableProvider.class)
		} else if(onWercker) {
			provider.getClass().equals(WerckerProvider.class)
		} else {
			provider.getClass().equals(UnknownContinuousIntegrationProvider.class)
		}
	}
}
