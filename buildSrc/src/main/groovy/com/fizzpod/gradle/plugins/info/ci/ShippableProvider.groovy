package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project
import org.gradle.api.provider.ProviderFactory

class ShippableProvider extends AbstractContinuousIntegrationProvider {

    ShippableProvider(ProviderFactory providerFactory) {
        super(providerFactory)
    }

    @Override
    boolean supports(Project project) {
        getEnvironmentVariable('SHIPPABLE') != null
    }

    @Override
    String calculateBuildNumber(Project project) {
        getEnvironmentVariable('SHIPPABLE_JOB_NUMBER')
    }

    @Override
    String calculateBuildId(Project project) {
        getEnvironmentVariable('SHIPPABLE_JOB_ID')
    }

    @Override
    String calculateHost(Project project) {
        getEnvironmentVariable('BUILD_URL')
    }

    @Override
    String calculateJob(Project project) {
        getEnvironmentVariable('SHIPPABLE_REPO_SLUG') + ":" +
                getEnvironmentVariable('BRANCH')
    }

    @Override
    String calculateBuildUrl(Project project) {
        return calculateHost(project)
    }
}
