

# Gradle Extended Info Plugin
A plugin extending the capability of the Nebula [gradle-info-plugin](https://github.com/nebula-plugins/gradle-info-plugin "nebula gradle-info-plugin").
```
buildscript {
    repositories { jcenter() }
    dependencies { classpath 'com.fizzpod.gradle-extended-info-plugin:0.1.+' }
}
```

This plugin uses version 2.2.0 of the gradle-info-plugin.

# Extended Capabilities
## Extended info-ci plugin
The Nebula gradle-info-plugin provides ci-info only for [Jenkins](http://jenkins-ci.org/ "Jenkins-CI"), but not for the many other continuous integration servers. This plugin provides ci-info for the following CI servers:
- [Jenkins](http://jenkins-ci.org/), (the default Nebula implementation)
- [Drone.io](https://drone.io/) [![Build Status](https://drone.io/github.com/boxheed/gradle-extended-info-plugin/status.png)](https://drone.io/github.com/boxheed/gradle-extended-info-plugin/latest)
- [Shippable](https://www.shippable.com/) [![Build Status](https://api.shippable.com/projects/54f037b55ab6cc13528f0e61/badge?branchName=master)](https://app.shippable.com/projects/54f037b55ab6cc13528f0e61/builds/latest)
- [Wercker](http://wercker.com/)
- [Travis-CI](https://travis-ci.org/) [![Build Status](https://travis-ci.org/boxheed/gradle-extended-info-plugin.svg?branch=master)](https://travis-ci.org/boxheed/gradle-extended-info-plugin)

### Usage
`apply plugin 'com.fizzpod.info-ci'`

### Customisation
The customisation uses the Java Service Loader for finding and loading implementations of `nebula.plugin.info.ci.ContinuousIntegrationInfoProvider`. In the `META-INF/services` folder your library create a file called `nebula.plugin.info.ci.ContinuousIntegrationInfoProvider` and in it specify an implementation class one per line, e.g. to specify all of the built in providers the contents would look like:
```
nebula.plugin.info.ci.JenkinsProvider
com.fizzpod.gradle.plugins.info.ci.DroneIoProvider
com.fizzpod.gradle.plugins.info.ci.ShippableProvider
com.fizzpod.gradle.plugins.info.ci.WerckerProvider
com.fizzpod.gradle.plugins.info.ci.TravisProvider
```

You then only need to include your library on the build script classpath and your implementations will be picked up.

## info plugin
Like the Nebula gradle-info-plugin this plugin provides an uber plugin that applies the other plugins, in this case however it replaces the Nebula info-ci plugin with the one in this package. 
### Usage

`apply plugin 'com.fizzpod.info`