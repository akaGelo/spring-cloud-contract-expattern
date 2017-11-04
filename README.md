
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/ru.vyukov/spring-cloud-contract-expattern/badge.svg)](https://maven-badges.herokuapp.com/maven-central/ru.vyukov/spring-cloud-contract-expattern)

```xml
<dependency>
    <groupId>ru.vyukov</groupId>
    <artifactId>spring-cloud-contract-expattern</artifactId>
    <version>0.2</version>
    <scope>test</scope>
</dependency>

```

## Example usage

````groovy
package contracts

import org.springframework.cloud.contract.spec.Contract

import ru.vyukov.contract.RequestPatterns;
import ru.vyukov.contract.ResponsePatterns;




Contract.make {
    name("post example")
    request {
        method 'POST'
        urlPath("/targets/") {

            body([
                    targetType: "FILESYSTEM",
                    trigger   : $(RequestPatterns.anyCronExpression()),
                    path      : $(consumer(anyNonEmptyString()), producer("/etc/")),
            ])
        }
        headers {contentType(applicationJson())}
    }

    response {
        status 200
        body([
                backupTargetId: anyNonEmptyString(),
                trigger       : $(ResponsePatterns.anyCronExpression()),
                targetType    : "FILESYSTEM",
                path          : $(consumer(fromRequest().body('$.path')), producer(fromRequest().body('$.path')))

        ])
    }
}


````