tasks.register("clean", Delete::class).configure {
    group = "build"
    delete(rootProject.buildDir)
}