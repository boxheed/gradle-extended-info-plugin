[![CircleCI](https://circleci.com/gh/boxheed/gradle-extended-info-plugin/tree/master.svg?style=shield)](https://circleci.com/gh/boxheed/gradle-extended-info-plugin/tree/master)

# Gradle Extended Info Plugin

A plugin extending the capability of the Nebula [gradle-info-plugin](https://github.com/nebula-plugins/gradle-info-plugin "nebula gradle-info-plugin").

```
buildscript {
    repositories { mavenCentral() }
    dependencies { classpath 'com.fizzpod.gradle-extended-info-plugin:3.0.+' }
}
```

This plugin uses version 3.0.3 of the gradle-info-plugin.

# Extended Capabilities

## Extended info-ci plugin

The Nebula gradle-info-plugin provides ci-info only for [Jenkins](http://jenkins-ci.org/ "Jenkins-CI"), but not for the many other continuous integration servers. This plugin provides ci-info for the following CI servers:
- [Jenkins](http://jenkins-ci.org/), (the default Nebula implementation)
- [Drone.io](https://drone.io/)
- [Wercker](http://wercker.com/)
- [Travis-CI](https://travis-ci.org/)
- [Snap-CI](https://snap-ci.org/)
- [Bitbucket Pipelines Beta](https://bitbucket.org/product/features/pipelines)

### Usage

`apply plugin 'com.fizzpod.info'`

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

## Release Notes

### 3.1.0

* Support for BitBucket pipelines Beta

### 3.0.0

* Updated gradle-info-plugin to 3.0.3
* Updated supported version of gradle to 2.10

### 1.0.2

* Updated version of gradle-info-plugin to 2.2.2

### 1.0.1

* Updated version of gradle-info-plugin to 2.2.1

### 1.0.0

* Support for version 2.2.0 of the gradle-info-plugin

