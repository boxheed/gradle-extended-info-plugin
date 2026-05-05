/* (C) 2024-2026 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

class WerckerProvider extends AbstractContinuousIntegrationProvider {

    protected ProviderFactory providerFactory

    WerckerProvider(ProviderFactory providerFactory) {
        super(providerFactory)
        this.providerFactory = providerFactory
    }

    @Override
    boolean supports() {
        return providerFactory.environmentVariable('WERCKER_ROOT').getOrNull() != null
    }

    @Override
    Provider<String> buildNumber() {
        return providerFactory.environmentVariable('WERCKER_BUILD_ID')
    }

    @Override
    Provider<String> buildId() {
        return providerFactory.environmentVariable('WERCKER_BUILD_ID')
    }

    @Override
    Provider<String> host() {
        return providerFactory.environmentVariable('WERCKER_BUILD_URL')
    }

    @Override
    Provider<String> job() {
        return providerFactory.environmentVariable('WERCKER_BUILD_ID')
    }

    @Override
    Provider<String> buildUrl() {
        return host()
    }
}
