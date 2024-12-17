/* (C) 2024 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.UnknownContinuousIntegrationProvider
import org.gradle.api.Project
import org.gradle.api.provider.ProviderFactory

class EnvironmentHelper extends UnknownContinuousIntegrationProvider {

    EnvironmentHelper(ProviderFactory providerFactory) {
        super(providerFactory)
    }

    String getVariable(String key) {
        return super.getEnvironmentVariable(key)
    }
    


}
