<!--
  ~ SPDX-FileCopyrightText: 2023-2025 Enedis
  ~
  ~ SPDX-License-Identifier: MIT
  ~
  -->
# Grafana DSL

**Generate Grafana Dashboards as Code with Kotlin DSL**

Project forked from : https://github.com/yoomoney/grafana-dashboard-dsl

## Table of Contents

1. [Features](#features)
   - [Disclaimer](#Disclaimer)
2. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
3. [Usage](#usage)
    - [Basic Example](#basic-example)
    - [Advanced Example](#advanced-example)
4. [Contributing](#contributing)
5. [License](#license)
6. [Contact](#contact)
7. [Acknowledgements](#acknowledgements)

## Features

- **Kotlin DSL**: Define your Grafana dashboards using the power of Kotlin language instead of templating extensive and repetitive json file.
- **Modular and Reusable**: Create reusable components and templates for your dashboards.
- **Integration**: Easily integrate with your CI/CD pipelines for automated dashboard deployment.
- **Versionning**: Using the Kotlin DSL makes the code more readable and maintainable than json in git history
- **Alerts**: Alerts are supported in the dsl

### Disclaimer

Currently, we only support grafana **version 10** . In the future, we will, may be, support multiple version of grafana.
 All the grafana dashboard components are not supported yet. If you find something which does not exist, feel free to open a pull request :)

## Getting Started

### Prerequisites

- Kotlin 1.9.x or higher
- Maven 3.x.x or higher
- Grafana instance (for importing dashboards)

### Installation

1. **Create your dashboard project with grafana-dsl maven dependency**:
   ```xml
    <dependency>
        <groupId>fr.enedis</groupId>
        <artifactId>grafana-dsl</artifactId>
    </dependency>

### Usage

#### Basic Example

Here's a simple example of how to define a Grafana dashboard using the Kotlin DSL. 
To generate the json Grafana dashboard, just call myDashboard.toString() on it.

```kotlin
val myDashboard = dashboard("my CPU Dashboard") {
   uid = "my_dashboard_uid"
   tags += listOf("SYSTEM", "CPU")
   editable = true
   timeRange = TimeRange(from = now.minus(24.h), to = now)
   refresh = off
   panels {
      row("System", collapsed = true) {
         timeSeriesPanel(title = "CPU load average") {
            bounds = cols(2)
            metrics(elasticDatasource("datasource")) {
               metricsQuery {
                  metrics = metrics {
                     metric {
                        type = "max"
                        field = "value_numeric"
                        query = "what: system_load_average_1m AND meta.environment: myEnv"
                        timeField = "@timestamp"
                        queryType = "randomWalk"
                        groupBys = groupBys {
                           dateHistogram("@timestamp")
                        }
                     }
                  }
               }
            }
            fieldConfig {
               unit = DataUnit.PERCENT_0_1.unit
            }
            options {
               legend = TimeSeriesLegend.HIDDEN
            }
         }
      }
   }
}
```

### Contributing

We welcome contributions from the community! To contribute:

- Fork the repository.
- Create a new branch (git checkout -b feature-branch).
- Make your changes and commit them (git commit -am 'Add new feature').
- Push to the branch (git push origin feature-branch).
- Create a new Pull Request.

Please make sure to update tests as appropriate.

Don't hesitate to simply open issues if needed !

### License

This project is licensed under the MIT License - see the LICENSE file for details.

###  Contact

For any questions or feedback, please open an issue

###  Acknowledgements

Thanks to the Grafana community for inspiration and support.
Special thanks to our contributors for their valuable input.
Thanks for the original project : https://github.com/yoomoney/grafana-dashboard-dsl

