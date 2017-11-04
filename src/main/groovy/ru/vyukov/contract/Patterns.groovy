package ru.vyukov.contract

import groovy.transform.PackageScope
import org.springframework.cloud.contract.spec.internal.ClientDslProperty
import org.springframework.cloud.contract.spec.internal.DslProperty
import org.springframework.cloud.contract.spec.internal.ServerDslProperty

import java.util.function.BiFunction
import java.util.regex.Pattern


abstract class Patterns<T extends DslProperty> {

    private final static Random random = new Random()

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
        return createAndValidateProperty(ExRegexPatterns.POSITIVE_NUMBER, 42);
    }


    T anyCronExpression() {
        //0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
        return createAndValidateProperty(ExRegexPatterns.CRON_EXPRESSION, randomCronExpression())
    }


    private static String randomCronExpression() {
        //0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
        String template = "%s %s %s %s %s %s";
        Object[] vals = new Object[6]
        vals[0] = or(nextInt(59), "*", "*/2", "*/17") //seconds
        vals[1] = or(nextInt(59), "*", "*/2", "*/17") //minutes
        vals[2] = or(nextInt(23), "*", "*/10") //hour
        vals[3] = or(nextInt(1, 31), "*") //days of month
        vals[4] = or(nextInt(1, 12), "*") // month
        vals[5] = or(nextInt(1, 7), "*", "MON", "MON-FRI") // day of week
        return String.format(template, vals);
    }

    private static Object or(int intVal, String... otherVals) {
        if (random.nextBoolean() || 0 == otherVals.length) {
            return intVal;
        }
        return or(otherVals);
    }

    private static Object or(String... otherVals) {
        return otherVals[random.nextInt(otherVals.length)];
    }

    private static int nextInt(int bound) {
        return random.nextInt(bound)
    }

    /**
     * [start,stop]
     * @param start
     * @param bound
     * @return
     */
    private static int nextInt(int start, int stop) {
        return random.nextInt(stop) + start;
    }
}
