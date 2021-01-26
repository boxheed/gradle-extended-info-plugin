package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.ContinuousIntegrationInfoProvider;

import org.gradle.api.Project

class ServiceLoadedContinuousIntegrationInfoProvider implements ContinuousIntegrationInfoProvider {

    EnvironmentHelper environmentHelper;

    @Override
    boolean supports(Project project) {
        false;
    }

    @Override
    String calculateBuildNumber(Project project) {
        "CI_NUMBER"
    }

    @Override
    String calculateBuildId(Project project) {
        "CI_BUILD_ID_1"
    }

    @Override
    String calculateHost(Project project) {
        "CI_HOST"
    }

    @Override
    String calculateJob(Project project) {
        "CI_JOB"
    }

    @Override
    String calculateBuildUrl(Project project) {
        return "CI_BUILD_URL"
    }

    protected String getEnvironmentVariable(String envKey) {
        environmentHelper.getVariable(envKey);
    }
}
