package ru.vyukov.contract

import org.springframework.cloud.contract.spec.internal.DslProperty

import java.util.regex.Pattern

import static ru.vyukov.contract.RandomUtils.*


abstract class Patterns<T extends DslProperty> {


    private T createAndValidateProperty(Pattern pattern, Object object = null) {
        if (object) {
            String generatedValue = object as String
            boolean matches = pattern.matcher(generatedValue).matches()
            if (!matches) {
                throw new IllegalStateException("The generated value [${generatedValue}] doesn't match the pattern [${pattern.pattern()}]")
            }
            return createProperty(pattern, generatedValue)
        }
        return createProperty(pattern, object)
    }


    abstract protected T createProperty(Pattern pattern, Object generatedValue);


    T anyPositiveNumber() {
        int rndInt = nextInt(Integer.MAX_VALUE);
        return createAndValidateProperty(ExRegexPatterns.POSITIVE_NUMBER, rndInt);
    }

    T anyShortPositiveNaturalNumber() {
        int naturalRndShort = nextInt(Short.MAX_VALUE - 1) + 1;
        return createAndValidateProperty(ExRegexPatterns.SHORT_POSITIVE_NATURAL_NUMBER, naturalRndShort);
    }


    T anyCronExpression() {
        //0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
        return createAndValidateProperty(ExRegexPatterns.CRON_EXPRESSION, randomCronExpression())
    }


    T anyNetworkPort() {
        int offset = 1024;
        int randomPort = offset + nextInt(65534 - offset);
        return createAndValidateProperty(ExRegexPatterns.NETWORK_PORT, randomPort);
    }


    T anyMongoObjectId() {
        return createAndValidateProperty(ExRegexPatterns.MONGO_OBJECT_ID, randomMongoObjectId());
    }




}
