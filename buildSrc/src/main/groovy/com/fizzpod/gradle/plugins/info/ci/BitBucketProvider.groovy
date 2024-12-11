/* (C) 2024 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider
import org.gradle.api.Project
import org.gradle.api.provider.ProviderFactory

class BitBucketProvider extends AbstractContinuousIntegrationProvider {

    public static final String BITBUCKET_URL = 'https://bitbucket.org/'

    BitBucketProvider(ProviderFactory providerFactory) {
        super(providerFactory)
    }

    @Override
    boolean supports(Project project) {
        getEnvironmentVariable('BITBUCKET_REPO_SLUG') != null
    }

    @Override
    String calculateBuildNumber(Project project) {
        getEnvironmentVariable('BITBUCKET_COMMIT')
    }

    @Override
    String calculateBuildId(Project project) {
        getEnvironmentVariable('BITBUCKET_COMMIT')
    }

    @Override
    String calculateHost(Project project) {
        BITBUCKET_URL + getEnvironmentVariable('BITBUCKET_REPO_OWNER') +
                "/" + getEnvironmentVariable('BITBUCKET_REPO_SLUG')
    }

    @Override
    String calculateJob(Project project) {
        getEnvironmentVariable('BITBUCKET_REPO_SLUG') + ":" +
                getEnvironmentVariable('BITBUCKET_BRANCH')
    }
    
    @Override
    String calculateBuildUrl(Project project) {
        return calculateHost(project) + "/" + calculateBuildId(project)
    }
}
