<!--
  ~ SPDX-FileCopyrightText: 2023-2025 Enedis
  ~
  ~ SPDX-License-Identifier: MIT
  ~
  -->

# Grafana kotlin dsl

kotlin [dsl](https://www.jetbrains.com/mps/concepts/domain-specific-languages/) to write grafana dashboards as code.

### Examples:

Some examples can be found [here](./src/test/kotlin/fr/enedis/grafana/dsl) in test folder.

### Usage

- declare `delivere-maven-releases` as repository in your pom.xml:
    ```
  ...
   <repository>
      <id>delivere-maven-releases-zca</id>
      <name>repositoi-zca-releases-delivere</name>
      <url>https://proxy-zci-to-repositoi-zca.enedis.fr/artifactory/delivere-maven-releases</url>
   </repository>
  ...
  ```
  
  Reading from this repo needs authentication. So, you must set credentials (in `.m2/settings.xml` for example)
  ```
  ...
  <server>
    <id>delivere-maven-releases</id>
    <username>delivere-reader</username>
    <password>${token}</password>
   </server>
  ...
  ```

- declare the dsl as maven dependency:
  ```
  ...
  <dependency>
     <groupId>fr.enedis</groupId>
     <artifactId>grafana-dsl</artifactId>
     <version>${grafana-dsl.version}</version>
  </dependency>
  ...
  ```