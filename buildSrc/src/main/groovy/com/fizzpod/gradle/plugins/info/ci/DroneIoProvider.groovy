/* (C) 2024-2026 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

class DroneIoProvider extends AbstractContinuousIntegrationProvider {

    protected ProviderFactory providerFactory

	DroneIoProvider(ProviderFactory providerFactory) {
        super(providerFactory)
        this.providerFactory = providerFactory
    }

	@Override
	boolean supports() {
		return providerFactory.environmentVariable('DRONE').getOrNull() != null
	}

	@Override
	Provider<String> buildNumber() {
		return providerFactory.environmentVariable('DRONE_BUILD_NUMBER')
	}

	@Override
	Provider<String> buildId() {
		return providerFactory.environmentVariable('DRONE_BUILD_ID')
	}

	@Override
	Provider<String> host() {
		return providerFactory.environmentVariable('DRONE_BUILD_URL')
	}

	@Override
	Provider<String> job() {
		return providerFactory.provider { 
            String slug = providerFactory.environmentVariable('DRONE_REPO_SLUG').getOrNull()
            String branch = providerFactory.environmentVariable('DRONE_BRANCH').getOrNull()
            if (slug && branch) {
                return "${slug}:${branch}"
            }
            return slug
        }
	}

	@Override
	Provider<String> buildUrl() {
		return host()
	}
    
}
