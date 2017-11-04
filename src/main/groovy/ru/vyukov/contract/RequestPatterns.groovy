package ru.vyukov.contract

import com.sun.istack.internal.Pool.Impl
import groovy.transform.CompileStatic
import org.springframework.cloud.contract.spec.internal.ClientDslProperty
import org.springframework.cloud.contract.spec.internal.DslProperty
import org.springframework.cloud.contract.spec.internal.
        ServerDslProperty
import org.springframework.cloud.contract.spec.internal.ServerDslProperty

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


}