package com.fizzpod.gradle.plugins.info.ci;

import nebula.plugin.info.ci.ContinuousIntegrationInfoProvider
import nebula.plugin.info.ci.UnknownContinuousIntegrationProvider
import nebula.plugin.info.ci.JenkinsProvider

public class ContinuousIntegrationInfoProviderResolver {

	private static final DEFAULT_PROVIDERS = [
		new JenkinsProvider(),
		new DroneIoProvider(),
		new ShippableProvider(),
		new WerckerProvider(),
		new TravisProvider(),
		new SnapProvider(),
		new BitBucketProvider(),
		new UnknownContinuousIntegrationProvider()
	]

	private static ServiceLoader<ContinuousIntegrationInfoProvider> continuousIntegrationInfoProviderServiceLoader = ServiceLoader
			.load(ContinuousIntegrationInfoProvider.class);

	def all () {
		return continuousIntegrationInfoProviderServiceLoader.asList() + DEFAULT_PROVIDERS;
	}
			
	ContinuousIntegrationInfoProvider findProvider(project) {

		def provider = this.all().find { it.supports(project) }
       
        if (provider) {
            return provider
        } else {
            return new UnknownContinuousIntegrationProvider()
        }
	}

}
