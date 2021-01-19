/*
 *  Copyright 2017 Alexey Zhokhov
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*
 * @author <a href='mailto:alexey@zhokhov.com'>Alexey Zhokhov</a>
 */
buildscript {
    repositories {
        mavenLocal()
        jcenter()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

plugins {
    id "io.spring.dependency-management" version "1.0.9.RELEASE"
    id "com.adarshr.test-logger"
}

apply plugin: "org.springframework.boot"

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation(project(":graphql-datetime-spring-boot-starter"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:$graphqlSpringBootVersion")
    implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:$graphqlSpringBootVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

jar.enabled = false
uploadArchives.enabled = false
