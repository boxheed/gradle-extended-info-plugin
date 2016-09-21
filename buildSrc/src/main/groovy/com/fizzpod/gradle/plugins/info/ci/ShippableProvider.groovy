package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project

class ShippableProvider extends AbstractContinuousIntegrationProvider {

	@Override
	boolean supports(Project project) {
		getEnvironmentVariable('SHIPPABLE') != null
	}

	@Override
	String calculateBuildNumber(Project project) {
		getEnvironmentVariable('SHIPPABLE_JOB_NUMBER')
	}

	@Override
	String calculateBuildId(Project project) {
		getEnvironmentVariable('SHIPPABLE_JOB_ID')
	}

	@Override
	String calculateHost(Project project) {
		getEnvironmentVariable('BUILD_URL')
	}

	@Override
	String calculateJob(Project project) {
		getEnvironmentVariable('SHIPPABLE_REPO_SLUG') + ":" +
			getEnvironmentVariable('BRANCH')
	}

}
