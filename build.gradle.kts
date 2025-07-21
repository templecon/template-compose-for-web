import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
	alias(cm.plugins.composeMultiplatform)
	alias(cm.plugins.composeCompiler)
	libs.plugins.run {
		`version-catalog`
		alias(kotlin.multiplatform)
		alias(serialization)
	}
}
repositories {
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
	google()
	mavenCentral()
}

kotlin {
	@OptIn(ExperimentalWasmDsl::class)
	wasmJs {
		outputModuleName.set("main")
		browser {
			val rootDirPath = project.rootDir.path
			val projectDirPath = project.projectDir.path
			commonWebpackConfig {
				outputFileName = "main.js"
				devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
					static = (static ?: mutableListOf()).apply {
						// Serve sources to debug inside browser
						add(rootDirPath)
						add(projectDirPath)
					}
				}
			}
		}
		binaries.executable()
	}
	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(libs.sealization.json)
				implementation(compose.runtime)
				implementation(compose.foundation)
				implementation(compose.material3)
				implementation(compose.ui)
				
				implementation(compose.components.resources)
				implementation(compose.components.uiToolingPreview)
				implementation(cm.androidx.lifecycle.viewmodel)
				implementation(cm.androidx.lifecycle.runtimeCompose)
			}
		}
		val wasmJsMain by getting {
			dependencies {
			}
		}
	}
}