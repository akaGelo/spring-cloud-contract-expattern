package ru.vyukov.contract

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.cloud.contract.spec.internal.DslProperty

import java.util.regex.Pattern

@PackageScope
@CompileStatic
class ExPatternValueDslProperty {

    private final static Random random = new Random()


    private DslProperty createAndValidateProperty(Pattern pattern, Object object = null) {
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

    DslProperty anyCronExpression() {
        //0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
        return createAndValidateProperty(ExRegexPatterns.CRON_EXPRESSION, randomCronExpression(20))
    }

    DslProperty anyPositiveNumber() {
        return createAndValidateProperty(ExRegexPatterns.POSITIVE_NUMBER, this.random.nextInt(Integer.MAX_VALUE - 1) + 1)
    }


    private static String randomCronExpression(int length) {
        //0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
        String template = "%s %s %s %s %s %s";
        Object[] vals = new Object[6]
        vals[0] = or(random.nextInt(59), "*", "*/2", "*/17") //seconds
        vals[1] = or(nextInt(59), "*", "*/2", "*/17") //minutes
        vals[2] = or(nextInt(23), "*", "*/10") //hour
        vals[3] = or(nextInt(1, 31), "*") //days of month
        vals[4] = or(nextInt(1, 12), "*") // month
        vals[5] = or(nextInt(1, 7),"*", "MON", "MON-FRI") // day of week
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

    private DslProperty createProperty(Pattern pattern, Object generatedValue) {
        return new DslProperty(pattern, generatedValue)
    }
}
