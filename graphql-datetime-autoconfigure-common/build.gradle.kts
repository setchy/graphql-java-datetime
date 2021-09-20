plugins {
    groovy
    id("com.adarshr.test-logger")
    id("maven-publish-conventions")
    id("signing-conventions")
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    api(project(":graphql-java-datetime"))
    api("com.graphql-java-kickstart:graphql-spring-boot-autoconfigure:${Versions.graphQlSpringBoot}")
    api("org.springframework.boot:spring-boot-autoconfigure:${Versions.springBoot}")

    testImplementation("org.codehaus.groovy:groovy-all:${Versions.groovy}")
    testImplementation("org.springframework.boot:spring-boot-starter-web:${Versions.springBoot}")
    testImplementation("org.springframework.boot:spring-boot-starter-tomcat:${Versions.springBoot}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.springBoot}")
    testImplementation("org.springframework.boot:spring-boot-test:${Versions.springBoot}")
    testImplementation("org.spockframework:spock-spring:${Versions.spock}")
    testImplementation("com.graphql-java-kickstart:graphql-spring-boot-starter:${Versions.graphQlSpringBoot}")
}

tasks.withType<JavaCompile>().configureEach {
    javaCompiler.set(
        javaToolchains.compilerFor {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    )
}
