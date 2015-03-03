package com.fizzpod.gradle.plugins.info.ci

import nebula.plugin.info.ci.AbstractContinuousIntegrationProvider;

import org.gradle.api.Project

class SnapProvider extends AbstractContinuousIntegrationProvider {
	
	public static final String SNAP_CI_URL = 'https://snap-ci.org/';
	
	@Override
	boolean supports(Project project) {
		getEnvironmentVariable('SNAP_CI')
	}

	@Override
	String calculateBuildNumber(Project project) {
		getEnvironmentVariable('SNAP_PIPELINE_COUNTER')
	}

	@Override
	String calculateBuildId(Project project) {
		getEnvironmentVariable('SNAP_PIPELINE_COUNTER')
	}

	@Override
	String calculateHost(Project project) {
		SNAP_CI_URL
	}

	@Override
	String calculateJob(Project project) {
	getEnvironmentVariable('SNAP_PIPELINE_COUNTER') + ":" +
		getEnvironmentVariable('SNAP_COMMIT') + ":" + 
			getEnvironmentVariable('SNAP_BRANCH')
	}

	private String getEnvironmentVariable(String envKey) {
		System.getenv(envKey)
	}
}
