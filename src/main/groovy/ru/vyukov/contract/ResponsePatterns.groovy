package ru.vyukov.contract

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.cloud.contract.spec.internal.ClientDslProperty
import org.springframework.cloud.contract.spec.internal.DslProperty
import org.springframework.cloud.contract.spec.internal.ServerDslProperty

import java.util.regex.Pattern

@CompileStatic
class ResponsePatterns {

    private static Patterns<ServerDslProperty> impl = new Patterns<ServerDslProperty>() {
        protected ServerDslProperty createProperty(Pattern pattern, Object generatedValue) {
            return new ServerDslProperty(pattern, generatedValue);
        }
    }


    static ServerDslProperty anyCronExpression() {
        return impl.anyCronExpression();
    }

    static ServerDslProperty anyPositiveNumberS() {
        return impl.anyPositiveNumber();
    }

}