/* (C) 2025 */
/* SPDX-License-Identifier: Apache-2.0 */
package com.fizzpod.gradle.plugins.info

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class InfoPluginSpec extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    def "register plugin"() {
        setup:
            Project project = ProjectBuilder.builder().withProjectDir(temporaryFolder.getRoot()).build()
            project.getPluginManager().apply('java')

        when:
            def plugin = new InfoPlugin()
            plugin.apply(project)

        then:
            project.getExtensions().findByName("ciinfo") != null
    }
}
