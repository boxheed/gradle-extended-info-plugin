package com.fizzpod.gradle.plugins.info;

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

import com.fizzpod.gradle.plugins.info.ci.ContinuousIntegrationInfoPlugin

public class InfoPlugin implements Plugin<Project> {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoPlugin.class);

	void apply(Project project) {
		// Broker
		project.plugins.apply(InfoBrokerPlugin)

		// Collectors
		project.plugins.apply(BasicInfoPlugin)
		project.plugins.apply(ManifestOwnersPlugin)
		project.plugins.apply(ScmInfoPlugin)
		project.plugins.apply(ContinuousIntegrationInfoPlugin)
		project.plugins.apply(InfoJavaPlugin)

		// Reporting
		project.plugins.apply(InfoPropertiesFilePlugin)
		project.plugins.apply(InfoJarPropertiesFilePlugin)
		project.plugins.apply(InfoJarManifestPlugin)
	}
}