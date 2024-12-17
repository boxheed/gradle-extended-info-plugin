/* (C) 2024 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.CircleCIProvider
import nebula.plugin.info.ci.ContinuousIntegrationInfoProvider
import nebula.plugin.info.ci.JenkinsProvider
import nebula.plugin.info.ci.UnknownContinuousIntegrationProvider
import org.gradle.api.provider.ProviderFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory


public class ContinuousIntegrationInfoProviderResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContinuousIntegrationInfoProviderResolver.class)

    private final ProviderFactory providerFactory

    private defaultProviders = []

    private static ServiceLoader<ContinuousIntegrationInfoProvider> continuousIntegrationInfoProviderServiceLoader = ServiceLoader
    .load(ContinuousIntegrationInfoProvider.class)


    ContinuousIntegrationInfoProviderResolver(ProviderFactory providerFactory) {
        this.providerFactory = providerFactory
        defaultProviders = [
            new JenkinsProvider(providerFactory),
            new DroneIoProvider(providerFactory),
            new CircleCIProvider(providerFactory),
            new WerckerProvider(providerFactory),
            new TravisProvider(providerFactory),
            new BitBucketProvider(providerFactory),
            new UnknownContinuousIntegrationProvider(providerFactory)
        ]
    }

    def all () {
        def providers = continuousIntegrationInfoProviderServiceLoader.asList() + defaultProviders
        providers.each {
            applyEnvrionmentHelper(it)
        }
        return providers
    }

    ContinuousIntegrationInfoProvider findProvider(project) {

        def provider = this.all().find { it.supports(project) }

        if (provider) {
            LOGGER.info("Using provider " + provider.getClass())
        } else {
            provider = new UnknownContinuousIntegrationProvider()
        }

        applyEnvrionmentHelper(provider)
        return provider
    }
    
    def applyEnvrionmentHelper(provider) {
        if(provider.hasProperty("environmentHelper")) {
            LOGGER.info("Setting environment helper on " + provider)
            provider.environmentHelper = new EnvironmentHelper(this.providerFactory)
        }
    }
}
