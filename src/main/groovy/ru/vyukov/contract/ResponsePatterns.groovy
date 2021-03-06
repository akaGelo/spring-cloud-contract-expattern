package ru.vyukov.contract

import groovy.transform.CompileStatic
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

    static ServerDslProperty anyPositiveNumbers() {
        return impl.anyPositiveNumber();
    }

    static ServerDslProperty anyNetworkPort() {
        return impl.anyNetworkPort();
    }

    static ServerDslProperty anyShortPositiveNumber() {
        return impl.anyShortPositiveNaturalNumber();
    }

    static ServerDslProperty anyMongoObjectId(){
        return impl.anyMongoObjectId();
    }
}