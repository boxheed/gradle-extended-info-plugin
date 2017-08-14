package com.fizzpod.gradle.plugins.info.ci


import nebula.plugin.info.ci.JenkinsProvider
import nebula.plugin.info.ci.UnknownContinuousIntegrationProvider
import nebula.test.ProjectSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fizzpod.gradle.plugins.info.ci.ContinuousIntegrationInfoProviderResolver

class ContinuousIntegrationInfoProviderResolverSpec extends ProjectSpec {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContinuousIntegrationInfoProviderResolverSpec.class);

	def 'get all configured info providers'() {
		when:
		def resolver = new ContinuousIntegrationInfoProviderResolver()

		then:
		def providers = resolver.all()
		providers != null
		providers.size() == 8
		providers[0].getClass().equals(ServiceLoadedContinuousIntegrationInfoProvider.class)
		providers[1].getClass().equals(JenkinsProvider.class)
		providers[2].getClass().equals(DroneIoProvider.class)
		providers[3].getClass().equals(ShippableProvider.class)
		providers[4].getClass().equals(WerckerProvider.class)
		providers[5].getClass().equals(TravisProvider.class)
		providers[6].getClass().equals(BitBucketProvider.class)
		providers[7].getClass().equals(UnknownContinuousIntegrationProvider.class)
	}

	def 'Get provider for this CI server'() {
		when:
		def onJenkins = System.getenv('BUILD_NUMBER') && System.getenv('JOB_NAME')
		def onDrone = System.getenv('DRONE') != null
		def onShippable = System.getenv('SHIPPABLE') != null
		def onWercker = System.getenv('WERCKER_ROOT') != null
		def onTravis = System.getenv('TRAVIS') != null
		def onBitBucket = System.getenv('BITBUCKET_COMMIT') != null
		def resolver = new ContinuousIntegrationInfoProviderResolver()

		then:
		def provider = resolver.findProvider(project)
		
		if(onJenkins) {
			provider.getClass().equals(JenkinsProvider.class)
		} else if(onDrone) {
			provider.getClass().equals(ServiceLoadedContinuousIntegrationInfoProvider.class)
		} else if(onShippable) {
			provider.getClass().equals(ShippableProvider.class)
		} else if(onWercker) {
			provider.getClass().equals(WerckerProvider.class)
		} else if(onTravis) {
			provider.getClass().equals(TravisProvider.class)
		} else if(onBitBucket) {
			provider.getClass().equals(BitBucketProvider.class)
		} else {
			provider.getClass().equals(UnknownContinuousIntegrationProvider.class)
		}
	}
}
