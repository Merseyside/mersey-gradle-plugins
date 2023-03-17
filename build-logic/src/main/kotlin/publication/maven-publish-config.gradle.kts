import java.util.Base64

plugins {
    `maven-publish`
    signing
}

publishing {
    publications.withType<MavenPublication>().all {
        artifactId = project.name
        pom {
            name.set("Mersey Gradle Plugins")
            description.set("Optimization gradle extension plugins")
            url.set("https://github.com/Merseyside/mersey-gradle-plugin")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("Merseyside")
                    name.set("Ivan Sablin")
                    email.set("ivanklessablin@gmail.com")
                }
            }
            scm {
                connection.set("scm:git:ssh://github.com/Merseyside/mersey-gradle-plugins.git")
                developerConnection.set("scm:git:ssh://github.com/Merseyside/mersey-gradle-plugins.git")
                url.set("https://github.com/Merseyside/mersey-gradle-plugins")
            }
        }
    }
}

signing {
    val signingKeyId: String? = System.getenv("SIGNING_KEY_ID")
    val signingPassword: String? = System.getenv("SIGNING_PASSWORD")
    val signingKey: String? = System.getenv("SIGNING_KEY")?.let { base64Key ->
        val _base = base64Key.replace("\n", "")
        String(Base64.getDecoder().decode(_base))
    }
    if (signingKeyId != null) {
        useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
        sign(publishing.publications)
    }
}