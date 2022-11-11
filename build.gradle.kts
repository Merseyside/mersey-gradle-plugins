//allprojects {
//    plugins.withId("org.gradle.maven-publish") {
//        group = "io.github.merseyside"
//        version = catalogPlugins.versions.mersey.plugins.get()
//    }
//}

tasks.register("clean", Delete::class).configure {
    group = "build"
    delete(rootProject.buildDir)
}