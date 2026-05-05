/* (C) 2024-2026 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

class TravisProvider extends AbstractContinuousIntegrationProvider {

    public static final String TRAVIS_CI_URL = 'https://travis-ci.org/'

    protected ProviderFactory providerFactory

    TravisProvider(ProviderFactory providerFactory) {
        super(providerFactory)
        this.providerFactory = providerFactory
    }

    @Override
    boolean supports() {
        return providerFactory.environmentVariable('TRAVIS').getOrNull() != null
    }

    @Override
    Provider<String> buildNumber() {
        return providerFactory.environmentVariable('TRAVIS_JOB_NUMBER')
    }

    @Override
    Provider<String> buildId() {
        return providerFactory.environmentVariable('TRAVIS_BUILD_ID')
    }

    @Override
    Provider<String> host() {
        return providerFactory.provider { TRAVIS_CI_URL }
    }

    @Override
    Provider<String> job() {
        return providerFactory.provider { 
            String slug = providerFactory.environmentVariable('TRAVIS_REPO_SLUG').getOrNull()
            String buildId = providerFactory.environmentVariable('TRAVIS_BUILD_ID').getOrNull()
            if (slug && buildId) {
                return "${slug}:${buildId}"
            }
            return slug
        }
    }

    @Override
    Provider<String> buildUrl() {
        return host()
    }
}
