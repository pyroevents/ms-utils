plugins {
    alias(libs.plugins.kotlin)
    `java-library`
    `maven-publish`
}

val projectVersion: String by project
group = "net.pyroevents"
version = projectVersion

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    kotlin {
        compilerOptions {
            jvmToolchain(21)
        }
    }
    group = "net.pyroevents"
    version = projectVersion

    tasks.register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    publishing {
        publications {
            create<MavenPublication>("gpr") {
                from(components["java"])
                groupId = group as String
                version = projectVersion
                artifact(tasks.getByName("sourcesJar"))
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/pyroevents/ms-utils")
                credentials {
                    username = project.findProperty("pyro.gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                    password = project.findProperty("pyro.gpr.token") as String? ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}
tasks {
    build {
        enabled = false
    }
}