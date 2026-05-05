/* (C) 2024-2026 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info.ci

import javax.inject.Inject
import nebula.plugin.info.InfoBrokerPlugin
import nebula.plugin.info.ci.ContinuousIntegrationInfoExtension
import nebula.plugin.info.ci.ContinuousIntegrationInfoProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.IConventionAware
import org.gradle.api.provider.ProviderFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class ContinuousIntegrationInfoPlugin implements Plugin<Project> {

	private static final String BUILD_ID = 'Build-Id'

	private static final String BUILD_NUMBER = 'Build-Number'

	private static final String BUILD_JOB = 'Build-Job'

	private static final String BUILD_HOST = 'Build-Host'

	private static final Logger LOGGER = LoggerFactory.getLogger(ContinuousIntegrationInfoPlugin.class)
	protected Project project


	ContinuousIntegrationInfoProvider selectedProvider
	ContinuousIntegrationInfoExtension extension

	private final ProviderFactory providerFactory

    @Inject
    ContinuousIntegrationInfoPlugin(ProviderFactory providerFactory) {
        this.providerFactory = providerFactory
    }

	@Override
	void apply(Project project) {
		this.project = project

		selectedProvider = findProvider()
		LOGGER.info("Using provider: {}", selectedProvider)
		extension = project.extensions.create('ciinfo', ContinuousIntegrationInfoExtension)

		def extMapping = ((IConventionAware) extension).getConventionMapping()
		extMapping.host = { selectedProvider.host().getOrNull() }
		extMapping.job = { selectedProvider.job().getOrNull() }
		extMapping.buildNumber = { selectedProvider.buildNumber().getOrNull() }
		extMapping.buildId = { selectedProvider.buildId().getOrNull() }

		project.plugins.withType(InfoBrokerPlugin) { manifestPlugin ->
			manifestPlugin.add(BUILD_HOST) { extension.host }
			manifestPlugin.add(BUILD_JOB) { extension.job }
			manifestPlugin.add(BUILD_NUMBER) { extension.buildNumber }
			manifestPlugin.add(BUILD_ID) { extension.buildId }
		}
	}

	ContinuousIntegrationInfoProvider findProvider() {
		return new ContinuousIntegrationInfoProviderResolver(providerFactory).findProvider(project)
	}
}
