package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project

class BitBucketProvider extends AbstractContinuousIntegrationProvider {
	
	@Override
	boolean supports(Project project) {
		getEnvironmentVariable('BITBUCKET_REPO_SLUG')  != null
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
		getEnvironmentVariable('HOSTNAME')
	}

	@Override
	String calculateJob(Project project) {
		getEnvironmentVariable('BITBUCKET_REPO_SLUG') + ":" + 
			getEnvironmentVariable('BITBUCKET_BRANCH') + ":" +
			getEnvironmentVariable('BITBUCKET_COMMIT')
	}

	private String getEnvironmentVariable(String envKey) {
		System.getenv(envKey)
	}
}