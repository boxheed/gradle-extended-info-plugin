/* (C) 2024-2026 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

class BitBucketProvider extends AbstractContinuousIntegrationProvider {

    public static final String BITBUCKET_URL = 'https://bitbucket.org/'

    protected ProviderFactory providerFactory

    BitBucketProvider(ProviderFactory providerFactory) {
        super(providerFactory)
        this.providerFactory = providerFactory
    }

    @Override
    boolean supports() {
        return providerFactory.environmentVariable('BITBUCKET_REPO_SLUG').getOrNull() != null
    }

    @Override
    Provider<String> buildNumber() {
        return providerFactory.environmentVariable('BITBUCKET_COMMIT')
    }

    @Override
    Provider<String> buildId() {
        return providerFactory.environmentVariable('BITBUCKET_COMMIT')
    }

    @Override
    Provider<String> host() {
        return providerFactory.provider { 
            String owner = providerFactory.environmentVariable('BITBUCKET_REPO_OWNER').getOrNull()
            String slug = providerFactory.environmentVariable('BITBUCKET_REPO_SLUG').getOrNull()
            if (owner && slug) {
                return "${BITBUCKET_URL}${owner}/${slug}"
            }
            return null
        }
    }

    @Override
    Provider<String> job() {
        return providerFactory.provider { 
            String slug = providerFactory.environmentVariable('BITBUCKET_REPO_SLUG').getOrNull()
            String branch = providerFactory.environmentVariable('BITBUCKET_BRANCH').getOrNull()
            if (slug && branch) {
                return "${slug}:${branch}"
            }
            return slug
        }
    }
    
    @Override
    Provider<String> buildUrl() {
        return providerFactory.provider {
            String hostUrl = host().getOrNull()
            String id = buildId().getOrNull()
            if (hostUrl && id) {
                return "${hostUrl}/${id}"
            }
            return null
        }
    }
}
