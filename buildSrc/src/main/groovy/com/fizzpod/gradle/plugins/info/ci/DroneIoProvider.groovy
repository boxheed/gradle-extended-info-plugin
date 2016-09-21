package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project

class DroneIoProvider extends AbstractContinuousIntegrationProvider {

	@Override
	boolean supports(Project project) {
		getEnvironmentVariable('DRONE') != null
	}

	@Override
	String calculateBuildNumber(Project project) {
		getEnvironmentVariable('DRONE_BUILD_NUMBER')
	}

	@Override
	String calculateBuildId(Project project) {
		getEnvironmentVariable('DRONE_BUILD_ID')
	}

	@Override
	String calculateHost(Project project) {
		getEnvironmentVariable('DRONE_BUILD_URL')
	}

	@Override
	String calculateJob(Project project) {
		getEnvironmentVariable('DRONE_REPO_SLUG') + ":" +
			getEnvironmentVariable('DRONE_BRANCH')
	}

}
