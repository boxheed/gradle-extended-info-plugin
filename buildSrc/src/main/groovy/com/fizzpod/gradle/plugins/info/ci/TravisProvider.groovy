/* (C) 2024 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider
import org.gradle.api.Project
import org.gradle.api.provider.ProviderFactory

class TravisProvider extends AbstractContinuousIntegrationProvider {

    public static final String TRAVIS_CI_URL = 'https://travis-ci.org/'

    TravisProvider(ProviderFactory providerFactory) {
        super(providerFactory)
    }

    @Override
    boolean supports(Project project) {
        getEnvironmentVariable('TRAVIS')  != null
    }

    @Override
    String calculateBuildNumber(Project project) {
        getEnvironmentVariable('TRAVIS_JOB_NUMBER')
    }

    @Override
    String calculateBuildId(Project project) {
        getEnvironmentVariable('TRAVIS_BUILD_ID')
    }

    @Override
    String calculateHost(Project project) {
        TRAVIS_CI_URL
    }

    @Override
    String calculateJob(Project project) {
        getEnvironmentVariable('TRAVIS_REPO_SLUG') + ":" +
                getEnvironmentVariable('TRAVIS_BUILD_ID')
    }

    @Override
    String calculateBuildUrl(Project project) {
        return calculateHost(project)
    }
}
