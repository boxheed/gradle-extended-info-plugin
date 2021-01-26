package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.UnknownContinuousIntegrationProvider
import org.gradle.api.provider.ProviderFactory

import org.gradle.api.Project

class EnvironmentHelper extends UnknownContinuousIntegrationProvider {

    EnvironmentHelper(ProviderFactory providerFactory) {
        super(providerFactory)
    }

    String getVariable(String key) {
        return super.getEnvironmentVariable(key)
    }
    


}
