package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project

class BitBucketProvider extends AbstractContinuousIntegrationProvider {

	public static final String BITBUCKET_URL = 'https://bitbucket.org/';

	@Override
	boolean supports(Project project) {
		getEnvironmentVariable('BITBUCKET_REPO_SLUG') != null
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
		BITBUCKET_URL + getEnvironmentVariable('BITBUCKET_REPO_OWNER') +
			"/" + getEnvironmentVariable('BITBUCKET_REPO_SLUG')
	}

	@Override
	String calculateJob(Project project) {
		getEnvironmentVariable('BITBUCKET_REPO_SLUG') + ":" +
			getEnvironmentVariable('BITBUCKET_BRANCH')
	}

}
