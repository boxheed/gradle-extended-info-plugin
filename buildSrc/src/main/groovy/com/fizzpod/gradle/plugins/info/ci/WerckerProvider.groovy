package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project
import org.gradle.api.provider.ProviderFactory

class WerckerProvider extends AbstractContinuousIntegrationProvider {

    WerckerProvider(ProviderFactory providerFactory) {
        super(providerFactory)
    }

    @Override
    boolean supports(Project project) {
        getEnvironmentVariable('WERCKER_ROOT')  != null
    }

    @Override
    String calculateBuildNumber(Project project) {
        getEnvironmentVariable('WERCKER_BUILD_ID')
    }

    @Override
    String calculateBuildId(Project project) {
        getEnvironmentVariable('WERCKER_BUILD_ID')
    }

    @Override
    String calculateHost(Project project) {
        getEnvironmentVariable('WERCKER_BUILD_URL')
    }

    @Override
    String calculateJob(Project project) {
        getEnvironmentVariable('WERCKER_BUILD_ID')
    }

    @Override
    String calculateBuildUrl(Project project) {
        return calculateHost(project)
    }
}
