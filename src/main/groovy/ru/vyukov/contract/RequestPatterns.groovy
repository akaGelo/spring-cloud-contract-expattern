package ru.vyukov.contract

import groovy.transform.CompileStatic
import org.springframework.cloud.contract.spec.internal.ClientDslProperty

import java.util.regex.Pattern

@CompileStatic
class RequestPatterns {

    private static Patterns<ClientDslProperty> impl = new Patterns<ClientDslProperty>() {
        protected ClientDslProperty createProperty(Pattern pattern, Object generatedValue) {
            return new ClientDslProperty(pattern, generatedValue);
        }
    }

    static ClientDslProperty anyCronExpression() {
        return impl.anyCronExpression();
    }

    static ClientDslProperty anyPositiveNumber() {
        return impl.anyPositiveNumber();
    }

    static ClientDslProperty anyNetworkPort() {
        return impl.anyNetworkPort();
    }

    static ClientDslProperty anyShortPositiveNumber() {
        return impl.anyShortPositiveNaturalNumber();
    }

}