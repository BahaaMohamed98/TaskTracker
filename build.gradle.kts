plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("com.github.bahaa.tasktracker.Main")
}

group = "com.github.bahaa.tasktracker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.h2database/h2
    implementation("com.h2database:h2:2.3.232")

    // https://mvnrepository.com/artifact/com.zaxxer/HikariCP
    implementation("com.zaxxer:HikariCP:6.3.0")
    implementation("org.slf4j:slf4j-nop:2.0.9") // no logging

    implementation("info.picocli:picocli:4.7.7")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "com.github.bahaa.tasktracker.Main")
    }

    // Create a fat JAR with all dependencies
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        @Suppress("unused")
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}