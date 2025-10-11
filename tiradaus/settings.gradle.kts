rootProject.name = "tiradaus"

include(
    "common",
    "domain",
    "application",
    "adapters_rest",
    "adapters:db"
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}