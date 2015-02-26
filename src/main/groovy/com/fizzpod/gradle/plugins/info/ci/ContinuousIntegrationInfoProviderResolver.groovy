package com.fizzpod.gradle.plugins.info.ci;

import nebula.plugin.info.ci.ContinuousIntegrationInfoProvider
import nebula.plugin.info.ci.UnknownContinuousIntegrationProvider

public class ContinuousIntegrationInfoProviderResolver {

	private static ServiceLoader<ContinuousIntegrationInfoProvider> continuousIntegrationInfoProviderServiceLoader = ServiceLoader
			.load(ContinuousIntegrationInfoProvider.class);

	def all () {
		return continuousIntegrationInfoProviderServiceLoader.asList() << new UnknownContinuousIntegrationProvider();
	}
			
	ContinuousIntegrationInfoProvider findProvider(project) {

		def provider = continuousIntegrationInfoProviderServiceLoader.find { it.supports(project) }
       
        if (provider) {
            return provider
        } else {
            return new UnknownContinuousIntegrationProvider()
        }
	}

}
