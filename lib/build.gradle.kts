plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"

    `java-library`
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.github.cdimascio:dotenv-kotlin:6.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest()
        }
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
}
