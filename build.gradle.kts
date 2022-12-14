import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm
    kotlin("jvm") version "1.7.22"
}

group = "com.smyrgeorge"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    // IMPORTANT: must be last.
    mavenLocal()
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Log each test.
    testLogging { events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED) }

    // Print a summary after test suite.
    addTestListener(object : TestListener {
        override fun beforeSuite(suite: TestDescriptor) {}
        override fun beforeTest(testDescriptor: TestDescriptor) {}
        override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}
        override fun afterSuite(suite: TestDescriptor, result: TestResult) {
            // Wll match the outermost suite.
            if (suite.parent == null) {
                println("\nTest result: ${result.resultType}")
                val summary = "Test summary: ${result.testCount} tests, " +
                        "${result.successfulTestCount} succeeded, " +
                        "${result.failedTestCount} failed, " +
                        "${result.skippedTestCount} skipped"
                println(summary)
            }
        }
    })
}

// Disables the build of '**-plain.jar'
tasks.getByName<Jar>("jar") {
    enabled = true
    // Found here:
    // https://www.jetbrains.com/help/idea/create-your-first-kotlin-app.html#package-as-jar
    manifest { attributes["Main-Class"] = "com.smyrgeorge.MainKt" }
    configurations["compileClasspath"].forEach { file: File -> from(zipTree(file.absoluteFile)) }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
