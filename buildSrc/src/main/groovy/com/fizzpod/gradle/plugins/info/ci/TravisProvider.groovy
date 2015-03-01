package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project

class TravisProvider extends AbstractContinuousIntegrationProvider {
	
	public static final String TRAVIS_CI_URL = 'https://travis-ci.org/';
	
	@Override
	boolean supports(Project project) {
		getEnvironmentVariable('TRAVIS')
	}

	@Override
	String calculateBuildNumber(Project project) {
		getEnvironmentVariable('TRAVIS_JOB_NUMBER')
	}

	@Override
	String calculateBuildId(Project project) {
		getEnvironmentVariable('TRAVIS_BUILD_ID')
	}

	@Override
	String calculateHost(Project project) {
		TRAVIS_CI_URL
	}

	@Override
	String calculateJob(Project project) {
		getEnvironmentVariable('TRAVIS_REPO_SLUG') + ":" + 
			getEnvironmentVariable('TRAVIS_BUILD_ID')
	}

	private String getEnvironmentVariable(String envKey) {
		System.getenv(envKey)
	}
}
