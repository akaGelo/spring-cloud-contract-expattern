package groovy.ru.vyukov.contract

import org.springframework.cloud.contract.spec.internal.ClientDslProperty
import org.springframework.cloud.contract.spec.internal.DslProperty
import ru.vyukov.contract.Patterns

import java.util.regex.Pattern

class TestPatterns extends Patterns<ClientDslProperty> {

    ClientDslProperty createProperty(Pattern pattern, Object generatedValue) {
        return new ClientDslProperty(pattern, generatedValue);
    }
}
