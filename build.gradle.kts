plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("Main")
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

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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