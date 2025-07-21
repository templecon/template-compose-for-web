enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
		google()
		google {
			mavenContent {
				includeGroupAndSubgroups("androidx")
				includeGroupAndSubgroups("com.android")
				includeGroupAndSubgroups("com.google")
			}
		}
		mavenCentral()
	}
	versionCatalogs {
		create("libs") {
			from(files("libs.versions.toml"))
		}
		create("cm")
		{
			from(files("compose.versions.toml"))
		}
	}
}

pluginManagement {
	repositories {
		google {
			mavenContent {
				includeGroupAndSubgroups("androidx")
				includeGroupAndSubgroups("com.android")
				includeGroupAndSubgroups("com.google")
			}
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version ("0.9.0")
}
rootProject.name = "template"
