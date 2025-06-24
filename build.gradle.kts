plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("TaskApp")
}

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
        attributes("Main-Class" to "TaskApp")
    }

    // Create a fat JAR with all dependencies
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
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

tasks.register<JavaExec>("generateCompletion") {
    dependsOn("build")
    group = "application"
    description = "Generate bash completion script"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("picocli.AutoComplete")
    args("TaskApp")
    doLast {
        println("Completion script generated: task-cli_completion")
        println("To enable completion, run: source task-cli_completion")
    }
}