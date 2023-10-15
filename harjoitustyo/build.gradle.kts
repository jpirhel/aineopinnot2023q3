plugins {
    id("java")
    id("application") apply true
    id("jacoco")
    id("checkstyle")
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.example.Main")
}

checkstyle {
    toolVersion = ("8.17")
}

jacoco {
    toolVersion = "0.8.9"
}

repositories {
    mavenCentral()
}

dependencies {
    // implementation(platform("org.jxmapviewer:jxmapviewer2:2.5"))

    implementation("org.jxmapviewer:jxmapviewer2:2.5")

    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))

    implementation(files("lib/jxmapviewer2-2.6.jar"))
}

tasks.jar {
    manifest.attributes["Main-Class"] = "org.example.Main"

    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree)
    from(dependencies)

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}
