/* (C) 2024-2025 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info

import com.fizzpod.gradle.plugins.info.ci.ContinuousIntegrationInfoPlugin
import groovy.time.TimeCategory 
import groovy.time.TimeDuration
import nebula.plugin.info.InfoBrokerPlugin
import nebula.plugin.info.basic.BasicInfoPlugin
import nebula.plugin.info.basic.ManifestOwnersPlugin
import nebula.plugin.info.java.InfoJavaPlugin
import nebula.plugin.info.reporting.InfoJarManifestPlugin
import nebula.plugin.info.reporting.InfoJarPropertiesFilePlugin
import nebula.plugin.info.reporting.InfoPropertiesFilePlugin
import nebula.plugin.info.scm.ScmInfoPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class InfoPlugin implements Plugin<Project> {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoPlugin.class)

	void apply(Project project) {
		// Broker
		applyPlugin(project, InfoBrokerPlugin)

		// Collectors
		applyPlugin(project, BasicInfoPlugin)
		applyPlugin(project, ManifestOwnersPlugin)
		applyPlugin(project, ScmInfoPlugin)
		applyPlugin(project, ContinuousIntegrationInfoPlugin)
		applyPlugin(project, InfoJavaPlugin)

		// Reporting
		applyPlugin(project, InfoPropertiesFilePlugin)
		applyPlugin(project, InfoJarPropertiesFilePlugin)
		applyPlugin(project, InfoJarManifestPlugin)
	}

	void applyPlugin(def project, def plugin) {
		Date start = new Date()
		project.plugins.apply(plugin)

		Date stop = new Date()

		TimeDuration td = TimeCategory.minus( stop, start )
		println "${plugin} ${td}"
	}

}
