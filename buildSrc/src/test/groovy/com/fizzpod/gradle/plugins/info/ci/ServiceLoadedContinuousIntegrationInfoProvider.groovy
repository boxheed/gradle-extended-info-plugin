/* (C) 2024-2026 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.ContinuousIntegrationInfoProvider
import org.gradle.api.provider.Provider

class ServiceLoadedContinuousIntegrationInfoProvider implements ContinuousIntegrationInfoProvider {

    EnvironmentHelper environmentHelper

    @Override
    boolean supports() {
        false
    }

    @Override
    Provider<String> buildNumber() {
        "CI_NUMBER"
    }

    @Override
    Provider<String> buildId() {
        "CI_BUILD_ID_1"
    }

    @Override
    Provider<String> host() {
        "CI_HOST"
    }

    @Override
    Provider<String> job() {
        "CI_JOB"
    }

    @Override
    Provider<String> buildUrl() {
        return "CI_BUILD_URL"
    }

    protected String getEnvironmentVariable(String envKey) {
        environmentHelper.getVariable(envKey)
    }
}
